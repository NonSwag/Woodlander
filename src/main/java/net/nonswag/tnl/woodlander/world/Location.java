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
    @Nonnull
    private Direction direction = Direction.DOWN;
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
    public Location setDirection(@Nonnull Direction direction) {
        this.direction = direction;
        return this;
    }

    @Nonnull
    public Tile getTile() {
        int y = this.y / GamePanel.TILE_SIZE;
        int x = this.x / GamePanel.TILE_SIZE;
        if (getDirection().isDown()) return world.getMap().getTiles()[y + 1][x];
        if (getDirection().isRight()) return world.getMap().getTiles()[y][x + 1];
        return world.getMap().getTiles()[y][x];
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
        return new Location(getWorld(), getX(), getY()).setDirection(getDirection());
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public boolean isUp() {
            return equals(UP);
        }

        public boolean isDown() {
            return equals(DOWN);
        }

        public boolean isLeft() {
            return equals(LEFT);
        }

        public boolean isRight() {
            return equals(RIGHT);
        }
    }
}
