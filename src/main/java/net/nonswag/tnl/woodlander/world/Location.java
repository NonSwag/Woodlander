package net.nonswag.tnl.woodlander.world;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;

@Getter
@Setter
public class Location {

    @Nonnull
    private World world;
    private int x, y;

    public Location(@Nonnull World world, int x, int y) {
        this.world = world;
        set(x, y);
    }

    public void set(@Nonnull Location location) {
        set(location.world, location.x, location.y);
    }

    public void set(@Nonnull World world, int x, int y) {
        this.world = world;
        set(x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "world=" + world +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
