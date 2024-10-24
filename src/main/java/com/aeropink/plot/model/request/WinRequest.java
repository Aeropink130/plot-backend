package com.aeropink.plot.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WinRequest {
    private String playerId;
    private List<String> markedCards;
    private int currentCardIndex;
}
