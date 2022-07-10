package net.nonswag.tnl.woodlander.entities;

import lombok.Getter;
import net.nonswag.tnl.woodlander.Woodlander;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

@Getter
public abstract class Entity {

    @Nonnull
    private final Image[] images;

    protected Entity(@Nonnull String path, @Nonnull String... images) throws IOException {
        this.images = new Image[images.length];
        for (int i = 0; i < images.length; i++) {
            try (InputStream stream = Woodlander.class.getClassLoader().getResourceAsStream(path + images[i])) {
                if (stream != null) this.images[i] = ImageIO.read(stream);
            }
        }
    }
}
