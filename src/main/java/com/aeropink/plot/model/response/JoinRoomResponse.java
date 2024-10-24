package com.aeropink.plot.model.response;

import com.aeropink.plot.model.Player;
import com.aeropink.plot.model.PlayerCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomResponse {
    private String roomName;
    private List<Player> players;
    private List<PlayerCard> template;
}
