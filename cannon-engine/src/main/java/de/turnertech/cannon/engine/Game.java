package de.turnertech.cannon.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Game 2D";
    private static final long serialVersionUID = 1L;

    List<GameEntity> gameEntities;

    KeyboardInput keyboardInput = new KeyboardInput();

    private JFrame frame;
    private boolean running = false;

    public Game(List<GameEntity> gameEntities) { 
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.addKeyListener(keyboardInput);

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.createBufferStrategy(2);

        this.gameEntities = gameEntities;
        
    }

    public synchronized void start() {
        new Thread(this).start();
        running = true;
    }

    public synchronized void stop() {
        running = false;
    }


    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        double deltaTime = 0; // Seconds

        while (running) {
            long now = System.currentTimeMillis();
            deltaTime = (now - lastTime) * 0.001;
            lastTime = now;

            update(deltaTime);

            render();
        }
    }

    public void update(double deltaTime) {
        synchronized(keyboardInput.keysDown) {
            for(GameEntity entity : gameEntities) {
                for(Integer key : keyboardInput.getKeysDown()) {
                    entity.onKeyDown(key);
                }
            }
            keyboardInput.clearKeysDown();
        }

        synchronized(keyboardInput.keysHeld) {
            for(GameEntity entity : gameEntities) {
                for(Integer key : keyboardInput.getKeysHeld()) {
                    entity.onKeyHold(key);
                }
            }
        }

        synchronized(keyboardInput.keysUp) {
            for(GameEntity entity : gameEntities) {
                for(Integer key : keyboardInput.getKeysUp()) {
                    entity.onKeyUp(key);
                }
            }
            keyboardInput.clearKeysUp();
        }

        for(GameEntity entity : gameEntities) {
            entity.onUpdate(deltaTime);
        }
    }

    public void render() {

        BufferStrategy strategy = this.getBufferStrategy();

        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics graphics = strategy.getDrawGraphics();

                // Render to graphics

                // Clear Screen
                graphics.setColor(Color.RED);
                graphics.fillRect(0, 0, (int) getBounds().getWidth(), (int) getBounds().getHeight());

                // Draw Entities to Screen
                for(GameEntity entity : this.gameEntities) {
                    entity.paint(graphics);
                }

                // Dispose the graphics
                graphics.dispose();

                // Repeat the rendering if the drawing buffer contents
                // were restored
            } while (strategy.contentsRestored());

            // Display the buffer
            strategy.show();

        // Repeat the rendering if the drawing buffer was lost
        } while (strategy.contentsLost());
        
    }
}
