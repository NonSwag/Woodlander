package net.nonswag.tnl.woodlander.world.tiles;

import lombok.Getter;
import net.nonswag.tnl.woodlander.world.images.Images;

import javax.annotation.Nonnull;
import java.awt.*;

@Getter
public class Tile {

    private final int id;

    public Tile(int id) {
        this.id = id;
    }

    public boolean isCollidable() {
        return Images.valueOf(id).isCollidable();
    }

    @Nonnull
    public Image getImage() {
        return Images.valueOf(id).getImage();
    }
}
