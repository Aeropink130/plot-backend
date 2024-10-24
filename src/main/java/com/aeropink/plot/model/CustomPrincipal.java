package com.aeropink.plot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.Principal;

@Getter
@Setter
@AllArgsConstructor
public class CustomPrincipal implements Principal {
    private String name;
}
