package com.aeropink.plot.controller;

import com.aeropink.plot.model.Player;
import com.aeropink.plot.model.PlayerCard;
import com.aeropink.plot.model.Room;
import com.aeropink.plot.model.RoomUpdate;
import com.aeropink.plot.model.request.*;
import com.aeropink.plot.model.response.CreateRoomResponse;
import com.aeropink.plot.model.response.JoinRoomResponse;
import com.aeropink.plot.model.response.RoomResponse;
import com.aeropink.plot.service.CardService;
import com.aeropink.plot.service.RoomService;
import com.aeropink.plot.utils.RoomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private CardService cardService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Crear una sala
    @MessageMapping("/createRoom")
    public void createRoom(@Payload CreateRoomRequest request, Principal principal) {
        System.out.println("Host creando sala con principal.getName(): " + principal.getName());
        if (principal == null) {
            System.err.println("Principal is null");
            return;
        }

        // Crear la sala
        Room room = roomService.createRoom(request.getRoomName());

        // Crear el jugador anfitrión
        Player player = new Player(request.getPlayerId(), request.getPlayerName());

        // Generar plantilla de 16 cartas aleatorias para el anfitrión
        List<PlayerCard> allCards = CardService.getAllCards();
        Collections.shuffle(allCards);
        List<PlayerCard> playerTemplate = allCards.subList(0, 16);
        player.setTemplate(playerTemplate);

        room.addPlayer(player);

        // Respuesta al creador de la sala
        CreateRoomResponse response = new CreateRoomResponse();
        response.setPlayers(room.getPlayers());
        response.setRoomCode(room.getCode());
        response.setTemplate(playerTemplate); // Enviar la plantilla al host
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/roomCreated", response);

        // Notificar a todos en la sala (solo el anfitrión en este caso)
        RoomUpdate roomUpdate = new RoomUpdate(room.getPlayers(), false, room.getDifficulty());
        messagingTemplate.convertAndSend("/topic/room/" + room.getCode(), roomUpdate);
    }

    // Unirse a una sala
    @MessageMapping("/joinRoom")
    public void joinRoom(@Payload JoinRoomRequest request, Principal principal) {
        System.out.println("JoinRoomRequest: " + request +
                "roomCode: " + request.getRoomCode() +
                "playerId: " + request.getPlayerId()
        );
        System.out.println("Jugador uniéndose con principal.getName(): " + principal.getName());
        if (principal == null) {
            System.err.println("Principal is null in joinRoom");
            return;
        }

        Room room = roomService.getRoom(request.getRoomCode());
        if (room != null && !room.isGameStarted()) {
            Player player = new Player(request.getPlayerId(), request.getPlayerName());

            // Generar plantilla de 16 cartas aleatorias para el jugador
            List<PlayerCard> allCards = cardService.getAllCards(); // Implementa este método para obtener todas las cartas
            Collections.shuffle(allCards);
            List<PlayerCard> playerTemplate = allCards.subList(0, 16);
            player.setTemplate(playerTemplate);

            room.addPlayer(player);


            // imprimir todos los jugadores
            List<Player> players = room.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                System.out.println(players.get(i).getName());
                System.out.println(players.get(i).getId());
//                System.out.println(players.get(i).getTemplate());
            }

            // Enviar respuesta al jugador que se une
            JoinRoomResponse response = new JoinRoomResponse(room.getName(), room.getPlayers(), player.getTemplate());
            messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/joinedRoom", response);

            // Notificar a todos en la sala la actualización
            RoomUpdate roomUpdate = new RoomUpdate(room.getPlayers(), false, room.getShuffledDeck(), room.getDifficulty());
            messagingTemplate.convertAndSend("/topic/room/" + room.getCode(), roomUpdate);
        } else {
            // Sala no encontrada o juego ya iniciado
            messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/error", "La sala no existe o el juego ya ha comenzado.");
        }
    }

    // Iniciar el juego
    @MessageMapping("/startGame")
    public void startGame(@Payload StartGameRequest request, Principal principal) {
        boolean started = roomService.startGame(request.getRoomCode());
        if (started) {
            Room room = roomService.getRoom(request.getRoomCode());
            // Guarda el mazo mezclado en la sala
            room.setShuffledDeck(request.getShuffledDeck());

            // Notificar a todos en la sala que el juego ha iniciado y enviar el mazo mezclado y la dificultad
            RoomUpdate roomUpdate = new RoomUpdate(room.getPlayers(), true, room.getShuffledDeck(), room.getDifficulty());
            messagingTemplate.convertAndSend("/topic/room/" + room.getCode(), roomUpdate);
        }
    }

    @MessageMapping("/updateDifficulty")
    public void updateDifficulty(@Payload UpdateDifficultyRequest request, Principal principal) {
        Room room = roomService.getRoom(request.getRoomCode());
        if (room != null && !room.isGameStarted()) {
            room.setDifficulty(request.getDifficulty());

            // Notificar a todos en la sala la actualización
            RoomUpdate roomUpdate = new RoomUpdate(room.getPlayers(), false, room.getDifficulty());
            messagingTemplate.convertAndSend("/topic/room/" + room.getCode(), roomUpdate);
            System.out.println("Enviando RoomUpdate con dificultad: " + room.getDifficulty());

        } else {
            // Sala no encontrada o juego ya iniciado
            messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/error", "No se puede cambiar la dificultad en este momento.");
        }
    }

    @MessageMapping("/checkWin")
    public void checkWin(@Payload CheckWinRequest request, Principal principal) {
        Room room = roomService.getRoom(request.getRoomCode());
        if (room != null && room.isGameStarted()) {
            room.addWinRequest(request.getPlayerId(), request.getMarkedCards(), request.getCurrentCardIndex(), messagingTemplate);
        } else {
            messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/error", "No se puede procesar la solicitud de victoria en este momento.");
        }
    }

    // Salir de una sala
    @MessageMapping("/leaveRoom")
    public void leaveRoom(@Payload LeaveRoomRequest request, Principal principal) {
        roomService.leaveRoom(request.getRoomCode(), principal.getName());

        Room room = roomService.getRoom(request.getRoomCode());
        if (room != null) {
            // Notificar a todos en la sala la actualización
            RoomUpdate roomUpdate = new RoomUpdate(room.getPlayers(), false);
            messagingTemplate.convertAndSend("/topic/room/" + room.getCode(), roomUpdate);
        } else {
            // Si la sala ya no existe, puedes manejarlo según tus necesidades
        }
    }
}
