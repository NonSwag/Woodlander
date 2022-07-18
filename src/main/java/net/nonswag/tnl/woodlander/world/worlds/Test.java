package net.nonswag.tnl.woodlander.world.worlds;

import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.map.Map;
import net.nonswag.tnl.woodlander.world.map.MapParseException;

import javax.annotation.Nonnull;

public class Test extends World {

    public Test() throws MapParseException {
        super("Test", new Map("/worlds/test.data"));
    }

    @Nonnull
    @Override
    public Location getSpawnLocation() {
        return new Location(this, 240, 240);
    }
}
