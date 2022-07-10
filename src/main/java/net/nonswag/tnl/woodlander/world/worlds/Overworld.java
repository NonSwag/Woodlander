package net.nonswag.tnl.woodlander.world.worlds;

import net.nonswag.tnl.woodlander.world.Map;
import net.nonswag.tnl.woodlander.world.World;

import java.io.IOException;

public class Overworld extends World {

    public Overworld() throws IOException {
        super("Overworld", new Map("/worlds/overworld.data"));
    }
}
