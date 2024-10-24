package com.aeropink.plot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String id;
    private String name;
    private List<PlayerCard> template;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.template = new ArrayList<>(); // Inicializa la plantilla
    }
}
