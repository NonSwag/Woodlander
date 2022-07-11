package net.nonswag.tnl.woodlander.world.entities;

import lombok.Getter;
import lombok.Setter;
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
    private final Rectangle hitbox = new Rectangle(8, 16, 32, 32);
    @Nonnull
    private final Location location;
    private final int id = ID++;
    private int speed = 3;

    protected Entity(@Nonnull Location location) {
        this.location = location;
        location.getWorld().getEntities().add(this);
    }

    public void stepForward() {
        switch (getDirection()) {
            case UP -> move(getLocation().getX(), getLocation().getY() - getSpeed());
            case DOWN -> move(getLocation().getX(), getLocation().getY() + getSpeed());
            case LEFT -> move(getLocation().getX() - getSpeed(), getLocation().getY());
            case RIGHT -> move(getLocation().getX() + getSpeed(), getLocation().getY());
        }
    }

    public void move(int x, int y) {
        location.set(x, y);
    }

    public void move(@Nonnull Location location) {
        if (!location.getWorld().equals(getWorld())) {
            getWorld().getEntities().remove(this);
            location.getWorld().getEntities().add(this);
        }
        this.location.set(location);
    }

    @Nonnull
    public World getWorld() {
        return location.getWorld();
    }

    public void tick() {
        if (isMoving()) stepForward();
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
