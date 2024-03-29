package net.nonswag.tnl.woodlander.world.entities;

import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.images.Type;
import net.nonswag.tnl.woodlander.world.tiles.Tile;

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
        super(location, new Rectangle(8, 16, 31, 31));
    }

    @Override
    public void render(@Nonnull Graphics2D graphic) {
        graphic.drawImage(getModel().getImage(), getScreenX(), getScreenY(), null);
    }

    @Override
    public boolean isOnScreen() {
        return GamePanel.isOnScreen(getScreenX(), getScreenY());
    }

    @Override
    public void keyTyped(@Nonnull KeyEvent event) {
    }

    @Override
    public void keyPressed(@Nonnull KeyEvent event) {
        if (GamePanel.PAUSE) return;
        if (event.getKeyCode() == KeyEvent.VK_W) {
            getLocation().setDirection(Location.Direction.UP);
            up = true;
        } else if (event.getKeyCode() == KeyEvent.VK_S) {
            getLocation().setDirection(Location.Direction.DOWN);
            down = true;
        } else if (event.getKeyCode() == KeyEvent.VK_D) {
            getLocation().setDirection(Location.Direction.RIGHT);
            right = true;
        } else if (event.getKeyCode() == KeyEvent.VK_A) {
            getLocation().setDirection(Location.Direction.LEFT);
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
        return (up && getLocation().getDirection().equals(Location.Direction.UP)) ||
                (down && getLocation().getDirection().equals(Location.Direction.DOWN)) ||
                (right && getLocation().getDirection().equals(Location.Direction.RIGHT)) ||
                (left && getLocation().getDirection().equals(Location.Direction.LEFT));
    }

    @Nonnull
    private Type getModel() {
        if (isMoving() && state++ >= 40 / getSpeed()) {
            if (sprite == 0) sprite = 1;
            else sprite = 0;
            state = 0;
        }
        return GamePanel.PAUSE || sprite == 0 || !isMoving() ? switch (getLocation().getDirection()) {
            case UP -> Type.PLAYER_UP_1;
            case DOWN -> Type.PLAYER_DOWN_1;
            case LEFT -> Type.PLAYER_LEFT_1;
            case RIGHT -> Type.PLAYER_RIGHT_1;
        } : switch (getLocation().getDirection()) {
            case UP -> Type.PLAYER_UP_2;
            case DOWN -> Type.PLAYER_DOWN_2;
            case LEFT -> Type.PLAYER_LEFT_2;
            case RIGHT -> Type.PLAYER_RIGHT_2;
        };
    }

    @Override
    public boolean collides(int x, int y) {
        if (y < 0 || x < 0) return true;
        int size = GamePanel.TILE_SIZE;
        int up = y + getHitbox().y, down = y + getHitbox().y + getHitbox().height;
        int left = x + getHitbox().x, right = x + getHitbox().x + getHitbox().width;
        int upRow = up / size, downRow = down / size, leftColumn = left / size, rightColumn = right / size;
        if (upRow < 0 || rightColumn < 0) return true;
        Tile firstTile, secondTile;
        if (upRow >= getWorld().getMap().getTiles().length) return true;
        if (downRow >= getWorld().getMap().getTiles().length) return true;
        if (rightColumn >= getWorld().getMap().getTiles()[y / size].length) return true;
        if (leftColumn >= getWorld().getMap().getTiles()[y / size].length) return true;
        if (leftColumn >= getWorld().getMap().getTiles()[upRow].length) return true;
        if (rightColumn >= getWorld().getMap().getTiles()[upRow].length) return true;
        firstTile = getWorld().getMap().getTiles()[upRow][leftColumn];
        secondTile = getWorld().getMap().getTiles()[upRow][rightColumn];
        if (firstTile.isCollidable() || secondTile.isCollidable()) return true;
        if (leftColumn >= getWorld().getMap().getTiles()[downRow].length) return true;
        firstTile = getWorld().getMap().getTiles()[downRow][leftColumn];
        secondTile = getWorld().getMap().getTiles()[downRow][rightColumn];
        if (firstTile.isCollidable() || secondTile.isCollidable()) return true;
        if (leftColumn >= getWorld().getMap().getTiles()[upRow].length) return true;
        firstTile = getWorld().getMap().getTiles()[upRow][leftColumn];
        secondTile = getWorld().getMap().getTiles()[downRow][leftColumn];
        if (firstTile.isCollidable() || secondTile.isCollidable()) return true;
        firstTile = getWorld().getMap().getTiles()[upRow][rightColumn];
        secondTile = getWorld().getMap().getTiles()[downRow][rightColumn];
        return firstTile.isCollidable() || secondTile.isCollidable();
    }

    public static int getScreenX() {
        return GamePanel.getScreenWidth() / 2 - (GamePanel.TILE_SIZE / 2);
    }

    public static int getScreenY() {
        return GamePanel.getScreenHeight() / 2 - (GamePanel.TILE_SIZE / 2);
    }
}
