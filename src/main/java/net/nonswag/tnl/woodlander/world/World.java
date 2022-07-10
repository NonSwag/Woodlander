package net.nonswag.tnl.woodlander.world;

import lombok.Getter;
import net.nonswag.tnl.woodlander.world.entities.Entity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class World {

    @Nonnull
    private final Map map;
    @Nonnull
    private final String name;
    @Nonnull
    private final List<Entity> entities = new ArrayList<>();

    protected World(@Nonnull String name, @Nonnull Map map) {
        this.name = name;
        this.map = map;
    }

    @Nonnull
    public Location center() {
        int mid = map.getTiles().length / 2;
        return new Location(this, mid, map.getTiles()[mid].length / 2d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return name.equals(world.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
