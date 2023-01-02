package de.turnertech.cannon.engine;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    
    public static final Logger LOGGER;

    static {
        try {
            LogManager.getLogManager().readConfiguration(Log.class.getResourceAsStream("logging.properties"));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        LOGGER = Logger.getLogger("cannon");
    }

    private Log() {
        // This is purely a static class
    }
    
}
