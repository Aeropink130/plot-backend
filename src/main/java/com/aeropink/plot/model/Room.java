package com.aeropink.plot.model;

import com.aeropink.plot.model.request.WinRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String code;
    private String name;
    private List<Player> players = new ArrayList<>(); // Inicializar aquí
    private boolean gameStarted;
    private List<Card> shuffledDeck;
    private String difficulty;
    private List<Card> calledCards = new ArrayList<>();
    private Queue<WinRequest> winRequests = new LinkedList<>();
    private boolean gameFinished = false; // Para evitar múltiples ganadores


    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerId) {
        this.players.removeIf(p -> p.getId().equals(playerId));
    }

    // Método para agregar una solicitud de ganador
    public synchronized void addWinRequest(String playerId, List<String> markedCards, int currentCardIndex, SimpMessagingTemplate messagingTemplate) {
        winRequests.add(new WinRequest(playerId, markedCards, currentCardIndex));
        processWinRequests(messagingTemplate);
    }

    private void processWinRequests(SimpMessagingTemplate messagingTemplate) {
        while (!winRequests.isEmpty() && !gameFinished) {
            WinRequest winRequest = winRequests.poll();
            boolean hasWon = verifyWin(winRequest);
            if (hasWon) {
                gameFinished = true;
                RoomUpdate roomUpdate = new RoomUpdate(players, true, shuffledDeck, difficulty);
                roomUpdate.setWinnerId(winRequest.getPlayerId());
                messagingTemplate.convertAndSend("/topic/room/" + code, roomUpdate);
                break;
            } else {
                messagingTemplate.convertAndSendToUser(winRequest.getPlayerId(), "/queue/error", "Lo siento, aún no has ganado.");
            }
        }
    }

    private boolean verifyWin(WinRequest winRequest) {
        Player player = getPlayerById(winRequest.getPlayerId());
        if (player == null) {
            System.out.println("Jugador no encontrado: " + winRequest.getPlayerId());
            return false;
        }
        List<PlayerCard> playerTemplate = player.getTemplate();

        // Reconstruir la lista de cartas llamadas hasta el índice actual
        List<Card> calledCards = shuffledDeck.subList(0, winRequest.getCurrentCardIndex() + 1);

        System.out.println("Verificando victoria para el jugador: " + player.getId());
        System.out.println("Cartas llamadas (" + calledCards.size() + "): " + calledCards.stream().map(Card::getNombre).collect(Collectors.toList()));
        System.out.println("Plantilla del jugador (" + playerTemplate.size() + "): " + playerTemplate.stream().map(PlayerCard::getNombre).collect(Collectors.toList()));

        // Verificar que todas las cartas de la plantilla están en las cartas llamadas
        for (PlayerCard card : playerTemplate) {
            boolean cardHasBeenCalled = calledCards.stream()
                    .anyMatch(calledCard -> calledCard.getNombre().equals(card.getNombre()));
            if (!cardHasBeenCalled) {
                System.out.println("La carta '" + card.getNombre() + "' no ha sido llamada aún.");
                return false;
            }
        }
        System.out.println("El jugador " + player.getId() + " ha ganado.");
        return true;
    }


    public synchronized void addCalledCard(Card card) {
        calledCards.add(card);
    }

    public List<Card> getCalledCards() {
        return new ArrayList<>(calledCards);
    }

    public Player getPlayerById(String playerId) {
        for (Player player : players) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }
}
