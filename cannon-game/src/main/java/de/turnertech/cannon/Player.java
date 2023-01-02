package de.turnertech.cannon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import de.turnertech.cannon.engine.GameEntity;

public class Player extends GameEntity {
    
    private Point position = new Point(100, 100);

    @Override
    public void onKeyHold(int keyCode) {        
        switch(keyCode) {
            case KeyEvent.VK_W:
            position.translate(0, 1);
            return;

            case KeyEvent.VK_A:
            position.translate(-1, 0);
            return;

            case KeyEvent.VK_S:
            position.translate(0, -1);
            return;

            case KeyEvent.VK_D:
            position.translate(1, 0);
            return;

            default:
            return;
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect((int)position.getX(), (int)position.getY(), 100, 50);
    }

}
