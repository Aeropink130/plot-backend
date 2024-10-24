package com.aeropink.plot.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequest {
    private String roomName;
    private String playerId;
    private String playerName;
}
