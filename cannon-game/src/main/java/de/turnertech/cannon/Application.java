package de.turnertech.cannon;

import java.io.IOException;
import java.util.Locale;

import de.turnertech.cannon.engine.Game;
import de.turnertech.cannon.engine.Log;

public class Application {
 
    /**
     * Gets called before the main method. Its all just application setup stuff, and setting the Locale to english
     * so that console output is the same for everyone.
     */
    static {
        try {
            System.getProperties().load(Application.class.getResourceAsStream("game.properties"));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Locale.setDefault(Locale.ENGLISH);
    }

    public static void main(String[] args) {
        
        Log.LOGGER.info("Starting up the Game");
        Game theGame = new Game();

        Log.LOGGER.fine("Creating entities...");
        theGame.addGameEntity(new Player());

        Log.LOGGER.fine("Running the game...");
        theGame.start();
    }
    
}
