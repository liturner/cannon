package de.turnertech.cannon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.turnertech.cannon.engine.Game;
import de.turnertech.cannon.engine.GameEntity;
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
        Game theGame;

        Log.LOGGER.info("Creating entities...");
        List<GameEntity> gameEntities = new ArrayList<>();
        gameEntities.add(new Player());
        theGame = new Game(gameEntities);

        Log.LOGGER.info("Running the game...");
        theGame.start();
    }
    
}
