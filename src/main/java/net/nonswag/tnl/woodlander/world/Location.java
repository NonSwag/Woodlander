package net.nonswag.tnl.woodlander.world;

import lombok.Getter;
import lombok.Setter;
import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.tiles.Tile;

import javax.annotation.Nonnull;

@Getter
@Setter
public class Location implements Cloneable {

    @Nonnull
    private World world;
    private int x, y;

    public Location(@Nonnull World world, int x, int y) {
        this.world = world;
        set(x, y);
    }

    @Nonnull
    public Location set(@Nonnull Location location) {
        return set(location.world, location.x, location.y);
    }

    @Nonnull
    public Location set(@Nonnull World world, int x, int y) {
        this.world = world;
        return set(x, y);
    }

    @Nonnull
    public Location set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Nonnull
    public Tile getTile() {
        return world.getMap().getTiles()[getY() / GamePanel.TILE_SIZE][getX() / GamePanel.TILE_SIZE];
    }

    @Override
    public String toString() {
        return "Location{" +
                "world=" + world +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Location clone() {
        return new Location(getWorld(), getX(), getY());
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
