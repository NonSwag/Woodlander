package net.nonswag.tnl.woodlander.world.worlds;

import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.map.Map;
import net.nonswag.tnl.woodlander.world.map.MapParseException;

import javax.annotation.Nonnull;

public class Overworld extends World {

    public Overworld() throws MapParseException {
        super("Overworld", new Map("/worlds/overworld.data"));
    }

    @Nonnull
    @Override
    public Location getSpawnLocation() {
        return new Location(this, 1584, 1248);
    }
}
