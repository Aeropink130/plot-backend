package com.aeropink.plot.model.response;

import com.aeropink.plot.model.Player;
import com.aeropink.plot.model.PlayerCard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateRoomResponse {
    private String roomCode;
    private List<Player> players;
    private List<PlayerCard> template; // Añadir este campo
}
