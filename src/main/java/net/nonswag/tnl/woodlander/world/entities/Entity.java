package net.nonswag.tnl.woodlander.world.entities;

import lombok.Getter;
import lombok.Setter;
import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Objects;
import java.util.function.Consumer;

@Getter
@Setter
public abstract class Entity {

    private static int ID = 0;

    @Nonnull
    private final Rectangle hitbox;
    @Nonnull
    private final Location location;
    @Nullable
    private Consumer<Location> collisionEvent;
    private final int id = ID++;
    private int speed = 4;

    protected Entity(@Nonnull Location location, @Nonnull Rectangle hitbox) {
        this.location = location;
        this.hitbox = hitbox;
        location.getWorld().getEntities().add(this);
    }

    public void stepForward() {
        switch (getLocation().getDirection()) {
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
        move(location.clone().set(x, y));
    }

    public void move(@Nonnull Location destination) {
        move(destination, false);
    }

    public void teleport(@Nonnull Location destination) {
        move(destination, true);
    }

    private void move(@Nonnull Location destination, boolean force) {
        if (force || !collides(destination.getX(), destination.getY())) {
            location.set(destination.getX(), destination.getY());
            if (destination.getWorld().equals(getWorld())) return;
            getWorld().getEntities().remove(this);
            destination.getWorld().getEntities().add(this);
            location.setWorld(destination.getWorld());
        } else if (collisionEvent != null) collisionEvent.accept(destination);
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
