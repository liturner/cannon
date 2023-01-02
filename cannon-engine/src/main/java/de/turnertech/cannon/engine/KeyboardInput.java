package de.turnertech.cannon.engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardInput extends java.awt.event.KeyAdapter {

    List<Integer> keysDown = new ArrayList<>();

    List<Integer> keysUp = new ArrayList<>();

    List<Integer> keysHeld = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        synchronized(keysDown) {
            if(!keysDown.contains(keyEvent.getKeyCode())) {
                keysDown.add(keyEvent.getKeyCode());
            }
        }
        synchronized(keysHeld) {
            if(!keysHeld.contains(keyEvent.getKeyCode())) {
                keysHeld.add(keyEvent.getKeyCode());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        synchronized(keysUp) {
            if(!keysUp.contains(keyEvent.getKeyCode())) {
                keysUp.add(keyEvent.getKeyCode());
            }
        }
        synchronized(keysHeld) {
            if(keysHeld.contains(keyEvent.getKeyCode())) {
                keysHeld.remove((Integer)keyEvent.getKeyCode());
            }
        }
    }

    public List<Integer> getKeysDown() {
        synchronized(keysDown) {
            return keysDown;
        }
    }

    public void clearKeysDown() {
        synchronized(keysDown) {
            keysDown.clear();
        }
    }

    public List<Integer> getKeysUp() {
        synchronized(keysUp) {
            return keysUp;
        }
    }

    public void clearKeysUp() {
        synchronized(keysUp) {
            keysUp.clear();
        }
    }
    
    public List<Integer> getKeysHeld() {
        synchronized(keysHeld) {
            return keysHeld;
        }
    }

}
