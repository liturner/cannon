package de.turnertech.cannon.engine;

import java.util.Optional;

public class GameEntity {
    
    public void onUpdate(double deltaTime) {/** Dafault impl is void */}

    public void onPaint(java.awt.Graphics graphics) {/** Dafault impl is void */}

    /**
     * Called once when a key is pressed down for the first time. 
     * 
     * @param keyCode Integer ID as per java.awt.event.KeyEvent.VK_... constants.
     */
    public void onKeyDown(int keyCode) {/** Dafault impl is void */}

    public void onKeyHold(int keyCode) {/** Dafault impl is void */}

    public void onKeyUp(int keyCode) {/** Dafault impl is void */}

    public void onCollision(GameEntity other) {/** Dafault impl is void */}

    /**
     * Implementing classes should override this function to return a Shape which 
     * will be used in collission detection.
     * 
     * @return
     */
    public Optional<java.awt.Rectangle> getCollisionBox() {
        return Optional.empty();
    }

}
