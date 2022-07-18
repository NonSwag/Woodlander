package net.nonswag.tnl.woodlander.world.images;

import lombok.Getter;
import net.nonswag.tnl.woodlander.ui.GamePanel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

@Getter
public class Images {

    @Nonnull
    private static final HashMap<Integer, Images> values = new HashMap<>();
    private static int ID = 10;

    @Nonnull
    public static final Images GRASS_0 = new Images("/images/tiles/grass/grass_0.png");
    @Nonnull
    public static final Images GRASS_1 = new Images("/images/tiles/grass/grass_1.png");

    @Nonnull
    public static final Images WATER_0 = new Images("/images/tiles/water/water_0.png", true);
    @Nonnull
    public static final Images WATER_1 = new Images("/images/tiles/water/water_1.png", true);
    @Nonnull
    public static final Images WATER_2 = new Images("/images/tiles/water/water_2.png", true);
    @Nonnull
    public static final Images WATER_3 = new Images("/images/tiles/water/water_3.png", true);
    @Nonnull
    public static final Images WATER_4 = new Images("/images/tiles/water/water_4.png", true);
    @Nonnull
    public static final Images WATER_5 = new Images("/images/tiles/water/water_5.png", true);
    @Nonnull
    public static final Images WATER_6 = new Images("/images/tiles/water/water_6.png", true);
    @Nonnull
    public static final Images WATER_7 = new Images("/images/tiles/water/water_7.png", true);
    @Nonnull
    public static final Images WATER_8 = new Images("/images/tiles/water/water_8.png", true);
    @Nonnull
    public static final Images WATER_9 = new Images("/images/tiles/water/water_9.png", true);
    @Nonnull
    public static final Images WATER_10 = new Images("/images/tiles/water/water_10.png", true);
    @Nonnull
    public static final Images WATER_11 = new Images("/images/tiles/water/water_11.png", true);
    @Nonnull
    public static final Images WATER_12 = new Images("/images/tiles/water/water_12.png", true);
    @Nonnull
    public static final Images WATER_13 = new Images("/images/tiles/water/water_13.png", true);

    @Nonnull
    public static final Images ROAD_0 = new Images("/images/tiles/road/road_0.png");
    @Nonnull
    public static final Images ROAD_1 = new Images("/images/tiles/road/road_1.png");
    @Nonnull
    public static final Images ROAD_2 = new Images("/images/tiles/road/road_2.png");
    @Nonnull
    public static final Images ROAD_3 = new Images("/images/tiles/road/road_3.png");
    @Nonnull
    public static final Images ROAD_4 = new Images("/images/tiles/road/road_4.png");
    @Nonnull
    public static final Images ROAD_5 = new Images("/images/tiles/road/road_5.png");
    @Nonnull
    public static final Images ROAD_6 = new Images("/images/tiles/road/road_6.png");
    @Nonnull
    public static final Images ROAD_7 = new Images("/images/tiles/road/road_7.png");
    @Nonnull
    public static final Images ROAD_8 = new Images("/images/tiles/road/road_8.png");
    @Nonnull
    public static final Images ROAD_9 = new Images("/images/tiles/road/road_9.png");
    @Nonnull
    public static final Images ROAD_10 = new Images("/images/tiles/road/road_10.png");
    @Nonnull
    public static final Images ROAD_11 = new Images("/images/tiles/road/road_11.png");
    @Nonnull
    public static final Images ROAD_12 = new Images("/images/tiles/road/road_12.png");

    @Nonnull
    public static final Images DIRT = new Images("/images/tiles/grass/dirt.png");
    @Nonnull
    public static final Images WALL = new Images("/images/tiles/objects/wall.png", true);
    @Nonnull
    public static final Images TREE = new Images("/images/tiles/objects/tree.png", true);

    @Nonnull
    public static final Images HUT = new Images("/images/tiles/objects/hut.png", true);
    @Nonnull
    public static final Images FLOOR = new Images("/images/tiles/objects/floor.png");
    @Nonnull
    public static final Images TABLE = new Images("/images/tiles/objects/table.png", true);

    @Nonnull
    public static final Images PLAYER_UP_1 = new Images(-1, "/images/entity/player/player_up_1.png");
    @Nonnull
    public static final Images PLAYER_UP_2 = new Images(-2, "/images/entity/player/player_up_2.png");
    @Nonnull
    public static final Images PLAYER_DOWN_1 = new Images(-3, "/images/entity/player/player_down_1.png");
    @Nonnull
    public static final Images PLAYER_DOWN_2 = new Images(-4, "/images/entity/player/player_down_2.png");
    @Nonnull
    public static final Images PLAYER_LEFT_1 = new Images(-5, "/images/entity/player/player_left_1.png");
    @Nonnull
    public static final Images PLAYER_LEFT_2 = new Images(-6, "/images/entity/player/player_left_2.png");
    @Nonnull
    public static final Images PLAYER_RIGHT_1 = new Images(-7, "/images/entity/player/player_right_1.png");
    @Nonnull
    public static final Images PLAYER_RIGHT_2 = new Images(-8, "/images/entity/player/player_right_2.png");

    @Nonnull
    private final String file;
    @Nullable
    private Image image;
    private final boolean collidable;
    private final int id;

    Images(int id, @Nonnull String file, boolean collidable) {
        if (values.containsKey(id)) throw new IllegalArgumentException("the id %s is already taken".formatted(id));
        this.collidable = collidable;
        this.file = file;
        this.id = id;
        values.put(id, this);
    }

    Images(@Nonnull String file, boolean collidable) {
        this(ID++, file, collidable);
    }

    Images(int id, @Nonnull String file) {
        this(id, file, false);
    }

    Images(@Nonnull String file) {
        this(ID++, file);
    }

    @Nonnull
    public static Images valueOf(int id) {
        return values.get(id);
    }

    @Nonnull
    public Image getImage() {
        if (image != null) return image;
        try (InputStream stream = getClass().getResourceAsStream(getFile())) {
            Objects.requireNonNull(stream, getFile());
            return image = ImageIO.read(stream).getScaledInstance(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, BufferedImage.SCALE_SMOOTH);
        } catch (Exception e) {
            throw e instanceof RuntimeException re ? re : new RuntimeException(e);
        }
    }

    public static int count() {
        return values.size();
    }
}
