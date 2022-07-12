package net.nonswag.tnl.woodlander.world.entities;

import net.nonswag.tnl.woodlander.GamePanel;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.images.Images;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener {

    private int state = 0;

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    private int sprite = 0;

    public Player(@Nonnull Location location) {
        super(location);
    }

    @Override
    public void render(@Nonnull Graphics2D graphic) {
        graphic.drawImage(getModel().getImage(), getScreenX(), getScreenY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    @Override
    public void keyTyped(@Nonnull KeyEvent event) {
    }

    @Override
    public void keyPressed(@Nonnull KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) {
            setDirection(Location.Direction.UP);
            up = true;
        } else if (event.getKeyCode() == KeyEvent.VK_S) {
            setDirection(Location.Direction.DOWN);
            down = true;
        } else if (event.getKeyCode() == KeyEvent.VK_D) {
            setDirection(Location.Direction.RIGHT);
            right = true;
        } else if (event.getKeyCode() == KeyEvent.VK_A) {
            setDirection(Location.Direction.LEFT);
            left = true;
        }
    }

    @Override
    public void keyReleased(@Nonnull KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_W) up = false;
        else if (event.getKeyCode() == KeyEvent.VK_S) down = false;
        else if (event.getKeyCode() == KeyEvent.VK_D) right = false;
        else if (event.getKeyCode() == KeyEvent.VK_A) left = false;
    }

    @Override
    public boolean isMoving() {
        return (up && getDirection().equals(Location.Direction.UP)) ||
                (down && getDirection().equals(Location.Direction.DOWN)) ||
                (right && getDirection().equals(Location.Direction.RIGHT)) ||
                (left && getDirection().equals(Location.Direction.LEFT));
    }

    @Nonnull
    private Images getModel() {
        if (isMoving() && state++ >= 13) {
            if (sprite == 0) sprite = 1;
            else sprite = 0;
            state = 0;
        }
        return sprite == 0 || !isMoving() ? switch (getDirection()) {
            case UP -> Images.PLAYER_UP_1;
            case DOWN -> Images.PLAYER_DOWN_1;
            case LEFT -> Images.PLAYER_LEFT_1;
            case RIGHT -> Images.PLAYER_RIGHT_1;
        } : switch (getDirection()) {
            case UP -> Images.PLAYER_UP_2;
            case DOWN -> Images.PLAYER_DOWN_2;
            case LEFT -> Images.PLAYER_LEFT_2;
            case RIGHT -> Images.PLAYER_RIGHT_2;
        };
    }

    public static int getScreenX() {
        return GamePanel.getScreenWidth() / 2 - (GamePanel.TILE_SIZE / 2);
    }

    public static int getScreenY() {
        return GamePanel.getScreenHeight() / 2 - (GamePanel.TILE_SIZE / 2);
    }
}
