package com.aeropink.plot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomUpdate {
    private List<Player> players;
    private boolean startGame;
    private List<Card> shuffledDeck;
    private String difficulty;
    private String winnerId; // Añade este campo si necesitas enviar el ID del ganador

    // Constructor completo
    public RoomUpdate(List<Player> players, boolean startGame, List<Card> shuffledDeck, String difficulty) {
        this.players = players;
        this.startGame = startGame;
        this.shuffledDeck = shuffledDeck;
        this.difficulty = difficulty;
    }

    // Constructor para actualizaciones sin mazo mezclado
    public RoomUpdate(List<Player> players, boolean startGame, String difficulty) {
        this.players = players;
        this.startGame = startGame;
        this.difficulty = difficulty;
        this.shuffledDeck = null;
    }

    // Si necesitas enviar solo la lista de jugadores y el estado de inicio
    public RoomUpdate(List<Player> players, boolean startGame) {
        this.players = players;
        this.startGame = startGame;
        this.difficulty = null;
        this.shuffledDeck = null;
    }
}