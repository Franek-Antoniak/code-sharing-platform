package com.code.sharing.platform.tools;

import java.util.UUID;

public class RandomStringUUID {
    public static String getUnique() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
