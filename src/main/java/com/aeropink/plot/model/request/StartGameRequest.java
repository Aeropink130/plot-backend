package com.aeropink.plot.model.request;

import com.aeropink.plot.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StartGameRequest {
    private String roomCode;
    private List<Card> shuffledDeck; // Añade esta propiedad
}
