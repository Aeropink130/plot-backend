package com.aeropink.plot.service;

import com.aeropink.plot.model.Room;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {
    private Map<String, Room> rooms = new ConcurrentHashMap<>();

    // Crear una nueva sala
    public Room createRoom(String name) {
        String code = generateRoomCode();
        Room room = new Room();
        room.setCode(code);
        room.setName(name);
        rooms.put(code, room);
        return room;
    }

    // Unirse a una sala existente
    public Room getRoom(String code) {
        return rooms.get(code);
    }

    // Añadir jugador a la sala (ya se hace en el controlador)

    // Iniciar el juego en una sala
    public boolean startGame(String code) {
        Room room = rooms.get(code);
        if (room != null && !room.isGameStarted()) {
            room.setGameStarted(true);
            return true;
        }
        return false;
    }

    // Salir de una sala
    public void leaveRoom(String code, String playerId) {
        Room room = rooms.get(code);
        if (room != null) {
            room.removePlayer(playerId);
            if (room.getPlayers().isEmpty()) {
                rooms.remove(code); // Eliminar sala si no hay jugadores
            }
        }
    }

    // Generar código único para la sala
    private String generateRoomCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        } while (rooms.containsKey(code));
        return code;
    }
}
