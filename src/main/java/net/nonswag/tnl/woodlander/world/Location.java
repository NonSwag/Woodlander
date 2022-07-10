package net.nonswag.tnl.woodlander.world;

import javax.annotation.Nonnull;

public record Location(@Nonnull World world, double x, double y) {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
