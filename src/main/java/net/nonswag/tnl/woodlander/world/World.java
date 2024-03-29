package net.nonswag.tnl.woodlander.world;

import lombok.Getter;
import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.entities.Entity;
import net.nonswag.tnl.woodlander.world.map.Map;

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
        int mid = map.getTiles().length >> 1;
        int tileSize = GamePanel.TILE_SIZE;
        return new Location(this, (map.getTiles()[mid - 1].length >> 1) * tileSize, mid * tileSize);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof World world && world.name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "World{" +
                "name='" + name + '\'' +
                '}';
    }

    @Nonnull
    public abstract Location getSpawnLocation();
}
