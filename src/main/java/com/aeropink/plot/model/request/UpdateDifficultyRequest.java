package com.aeropink.plot.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDifficultyRequest {
    private String roomCode;
    private String difficulty;
}
