package com.aeropink.plot.utils;

import java.security.SecureRandom;

public class RoomCodeGenerator {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRoomCode() {
        StringBuilder roomCode = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int character = random.nextInt(ALPHA_NUMERIC_STRING.length());
            roomCode.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return roomCode.toString();
    }
}
