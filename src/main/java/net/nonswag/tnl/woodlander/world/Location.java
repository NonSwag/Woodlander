package net.nonswag.tnl.woodlander.world;

import javax.annotation.Nonnull;

public record Location(@Nonnull World world, double x, double z) {
}
