package net.nonswag.tnl.woodlander.world.entities;

import lombok.Getter;
import lombok.Setter;
import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public abstract class Entity {

    private static int ID = 0;

    @Nonnull
    private Location.Direction direction = Location.Direction.DOWN;
    @Nonnull
    private final Rectangle hitbox;
    @Nonnull
    private final Location location;
    private final int id = ID++;
    private int speed = 4;

    protected Entity(@Nonnull Location location, @Nonnull Rectangle hitbox) {
        this.location = location;
        this.hitbox = hitbox;
        location.getWorld().getEntities().add(this);
    }

    public void stepForward() {
        switch (getDirection()) {
            case UP -> {
                for (int i = 0; i < getSpeed(); i++) move(getLocation().getX(), getLocation().getY() - 1);
            }
            case DOWN -> {
                for (int i = 0; i < getSpeed(); i++) move(getLocation().getX(), getLocation().getY() + 1);
            }
            case LEFT -> {
                for (int i = 0; i < getSpeed(); i++) move(getLocation().getX() - 1, getLocation().getY());
            }
            case RIGHT -> {
                for (int i = 0; i < getSpeed(); i++) move(getLocation().getX() + 1, getLocation().getY());
            }
        }
    }

    public void move(int x, int y) {
        if (!collides(x, y)) location.set(x, y);
    }

    public void move(@Nonnull Location location) {
        move(location.getX(), location.getY());
        if (location.getWorld().equals(getWorld())) return;
        getWorld().getEntities().remove(this);
        location.getWorld().getEntities().add(this);
        this.location.setWorld(location.getWorld());
    }

    public boolean collides(int x, int y) {
        return false;
    }

    @Nonnull
    public World getWorld() {
        return location.getWorld();
    }

    public void tick() {
        if (isMoving()) stepForward();
    }

    public boolean isOnScreen() {
        return GamePanel.isOnScreen(getLocation().getX(), getLocation().getY());
    }

    public abstract void render(@Nonnull Graphics2D graphic);

    public abstract boolean isMoving();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
