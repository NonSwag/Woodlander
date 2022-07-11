package net.nonswag.tnl.woodlander.world.worlds;

import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.map.Map;
import net.nonswag.tnl.woodlander.world.map.MapParseException;

public class Overworld extends World {

    public Overworld() throws MapParseException {
        super("Overworld", new Map("/worlds/overworld.data"));
    }
}
