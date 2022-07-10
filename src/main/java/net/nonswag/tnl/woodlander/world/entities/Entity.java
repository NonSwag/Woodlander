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
    private Location location;
    @Nonnull
    private Location.Direction direction = Location.Direction.DOWN;
    @Nonnull
    private final Rectangle hitbox = new Rectangle(8, 16, 32, 32);
    private final int id = ID++;
    private double speed = 3;

    protected Entity(@Nonnull Location location) {
        this.location = location;
        location.world().getEntities().add(this);
    }

    public void stepForward() {
        switch (getDirection()) {
            case UP -> move(getLocation().x(), getLocation().y() - getSpeed());
            case DOWN -> move(getLocation().x(), getLocation().y() + getSpeed());
            case LEFT -> move(getLocation().x() - getSpeed(), getLocation().y());
            case RIGHT -> move(getLocation().x() + getSpeed(), getLocation().y());
        }
    }

    public void move(double x, double y) {
        move(new Location(getWorld(), x, y));
    }

    public void move(@Nonnull Location location) {
        if (!location.world().equals(getWorld())) {
            getWorld().getEntities().remove(this);
            location.world().getEntities().add(this);
        }
        this.location = location;
    }

    @Nonnull
    public World getWorld() {
        return location.world();
    }

    public void tick() {
        if (isMoving()) stepForward();
    }

    public abstract void paint(@Nonnull Graphics2D graphic);

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
