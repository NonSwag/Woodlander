package net.nonswag.tnl.woodlander.world.images;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;

@Getter
public enum Images {
    DIRT("/images/tiles/grass/dirt.png"),
    GRASS_0("/images/tiles/grass/grass_0.png"),
    GRASS_1("/images/tiles/grass/grass_1.png"),

    FLOOR("/images/tiles/objects/floor.png"),
    TREE("/images/tiles/objects/tree.png", true),
    HUT("/images/tiles/objects/hut.png", true),
    TABLE("/images/tiles/objects/table.png", true),
    WALL("/images/tiles/objects/wall.png", true),

    ROAD_0("/images/tiles/road/road_0.png"),
    ROAD_1("/images/tiles/road/road_1.png"),
    ROAD_2("/images/tiles/road/road_2.png"),
    ROAD_3("/images/tiles/road/road_3.png"),
    ROAD_4("/images/tiles/road/road_4.png"),
    ROAD_5("/images/tiles/road/road_5.png"),
    ROAD_6("/images/tiles/road/road_6.png"),
    ROAD_7("/images/tiles/road/road_7.png"),
    ROAD_8("/images/tiles/road/road_8.png"),
    ROAD_9("/images/tiles/road/road_9.png"),
    ROAD_10("/images/tiles/road/road_10.png"),
    ROAD_11("/images/tiles/road/road_11.png"),
    ROAD_12("/images/tiles/road/road_12.png"),

    WATER_0("/images/tiles/water/water_0.png", true),
    WATER_1("/images/tiles/water/water_1.png", true),
    WATER_2("/images/tiles/water/water_2.png", true),
    WATER_3("/images/tiles/water/water_3.png", true),
    WATER_4("/images/tiles/water/water_4.png", true),
    WATER_5("/images/tiles/water/water_5.png", true),
    WATER_6("/images/tiles/water/water_6.png", true),
    WATER_7("/images/tiles/water/water_7.png", true),
    WATER_8("/images/tiles/water/water_8.png", true),
    WATER_9("/images/tiles/water/water_9.png", true),
    WATER_10("/images/tiles/water/water_10.png", true),
    WATER_11("/images/tiles/water/water_11.png", true),
    WATER_12("/images/tiles/water/water_12.png", true),
    WATER_13("/images/tiles/water/water_13.png", true),

    PLAYER_DOWN_1("/images/entity/player/player_down_1.png"),
    PLAYER_DOWN_2("/images/entity/player/player_down_2.png"),
    PLAYER_LEFT_1("/images/entity/player/player_left_1.png"),
    PLAYER_LEFT_2("/images/entity/player/player_left_2.png"),
    PLAYER_RIGHT_1("/images/entity/player/player_right_1.png"),
    PLAYER_RIGHT_2("/images/entity/player/player_right_2.png"),
    PLAYER_UP_1("/images/entity/player/player_up_1.png"),
    PLAYER_UP_2("/images/entity/player/player_up_2.png"),
    ;

    @Nonnull
    private final String file;
    private final boolean collidable;
    @Nullable
    private Image image;

    Images(@Nonnull String file, boolean collidable) {
        this.collidable = collidable;
        this.file = file;
    }

    Images(@Nonnull String file) {
        this(file, false);
    }

    @Nonnull
    public static Images valueOf(int id) {
        return values()[id];
    }

    @Nonnull
    public Image getImage() {
        if (image != null) return image;
        try (InputStream stream = getClass().getResourceAsStream(getFile())) {
            assert stream != null : getFile();
            return image = ImageIO.read(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
