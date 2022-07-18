package net.nonswag.tnl.woodlander.world.tiles;

import lombok.Getter;
import net.nonswag.tnl.woodlander.world.images.Type;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Objects;

@Getter
public class Tile {

    private final int id;

    public Tile(int id) {
        this.id = id;
    }

    public boolean isCollidable() {
        return getType().isCollidable();
    }

    @Nonnull
    public Image getImage() {
        return getType().getImage();
    }

    @Nonnull
    public Type getType() {
        return Type.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return id == tile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "type=" + getType() +
                '}';
    }
}
