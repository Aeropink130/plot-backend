package com.aeropink.plot.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRoomRequest {
    private String roomCode;
    private String playerId;
    private String playerName;
}
