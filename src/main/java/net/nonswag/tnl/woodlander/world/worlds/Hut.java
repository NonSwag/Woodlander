package net.nonswag.tnl.woodlander.world.worlds;

import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.map.Map;
import net.nonswag.tnl.woodlander.world.map.MapParseException;

import javax.annotation.Nonnull;

public class Hut extends World {

    public Hut() throws MapParseException {
        super("Hut", new Map("/worlds/hut.data"));
    }

    @Nonnull
    @Override
    public Location getSpawnLocation() {
        return new Location(this, 240, 240);
    }
}
