package de.turnertech.cannon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import de.turnertech.cannon.engine.GameEntity;

public class Player extends GameEntity {
    
    private Point2D position = new Point2D.Double(100, 100);

    private Point direction = new Point(0, 0);

    @Override
    public void onKeyHold(int keyCode) {   
        
        direction.setLocation(0, 0);
        
        switch(keyCode) {
            case KeyEvent.VK_W:
            direction.translate(0, 1);
            return;

            case KeyEvent.VK_A:
            direction.translate(-1, 0);
            return;

            case KeyEvent.VK_S:
            direction.translate(0, -1);
            return;

            case KeyEvent.VK_D:
            direction.translate(1, 0);
            return;

            default:
            return;
        }
    }

    @Override
    public void onPaint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect((int)position.getX(), (int)position.getY(), 100, 50);
    }

    @Override
    public void onUpdate(double deltaTime) {
        // 10 Pixels per second
        Point2D.Double velocity = new Point2D.Double(direction.getX() * 10 * deltaTime, direction.getY() * 10 * deltaTime);
        position = new Point2D.Double(position.getX() + velocity.getX(), position.getY() + velocity.getY());
    }

}
