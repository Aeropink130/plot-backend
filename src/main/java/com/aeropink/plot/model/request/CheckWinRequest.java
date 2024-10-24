package com.aeropink.plot.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckWinRequest {
    private String roomCode;
    private String playerId;
    private List<String> markedCards;
    private int currentCardIndex;
}
