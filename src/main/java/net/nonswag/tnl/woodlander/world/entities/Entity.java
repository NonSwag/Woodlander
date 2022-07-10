package net.nonswag.tnl.woodlander.world.entities;

import lombok.Getter;
import net.nonswag.tnl.woodlander.Woodlander;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Getter
public abstract class Entity {

    private static int ID = 0;

    @Nonnull
    private final Image[] images;
    @Nonnull
    private Location location;
    private final int id = ID++;

    protected Entity(@Nonnull Location location, @Nonnull String path, @Nonnull String... images) throws IOException {
        this.location = location;
        this.images = new Image[images.length];
        for (int i = 0; i < images.length; i++) {
            try (InputStream stream = Woodlander.class.getClassLoader().getResourceAsStream(path + images[i])) {
                if (stream != null) this.images[i] = ImageIO.read(stream);
            }
        }
        Woodlander.ENTITIES.add(this);
    }

    public void move(double x, double z) {
        move(new Location(getWorld(), x, z));
    }

    public void move(@Nonnull Location location) {
        if (!location.world().equals(getWorld())) {
            this.location.world().getEntities().remove(this);
            location.world().getEntities().add(this);
        }
        this.location = location;
    }

    @Nonnull
    public World getWorld() {
        return location.world();
    }

    public void paint(@Nonnull Graphics2D graphic) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
