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
public class Type {

    @Nonnull
    private static final HashMap<Integer, Type> values = new HashMap<>();
    private static int ID = 10;

    @Nonnull
    public static final Type GRASS_0 = new Type("/images/tiles/grass/grass_0.png");
    @Nonnull
    public static final Type GRASS_1 = new Type("/images/tiles/grass/grass_1.png");

    @Nonnull
    public static final Type WATER_0 = new Type("/images/tiles/water/water_0.png", true);
    @Nonnull
    public static final Type WATER_1 = new Type("/images/tiles/water/water_1.png", true);
    @Nonnull
    public static final Type WATER_2 = new Type("/images/tiles/water/water_2.png", true);
    @Nonnull
    public static final Type WATER_3 = new Type("/images/tiles/water/water_3.png", true);
    @Nonnull
    public static final Type WATER_4 = new Type("/images/tiles/water/water_4.png", true);
    @Nonnull
    public static final Type WATER_5 = new Type("/images/tiles/water/water_5.png", true);
    @Nonnull
    public static final Type WATER_6 = new Type("/images/tiles/water/water_6.png", true);
    @Nonnull
    public static final Type WATER_7 = new Type("/images/tiles/water/water_7.png", true);
    @Nonnull
    public static final Type WATER_8 = new Type("/images/tiles/water/water_8.png", true);
    @Nonnull
    public static final Type WATER_9 = new Type("/images/tiles/water/water_9.png", true);
    @Nonnull
    public static final Type WATER_10 = new Type("/images/tiles/water/water_10.png", true);
    @Nonnull
    public static final Type WATER_11 = new Type("/images/tiles/water/water_11.png", true);
    @Nonnull
    public static final Type WATER_12 = new Type("/images/tiles/water/water_12.png", true);
    @Nonnull
    public static final Type WATER_13 = new Type("/images/tiles/water/water_13.png", true);

    @Nonnull
    public static final Type ROAD_0 = new Type("/images/tiles/road/road_0.png");
    @Nonnull
    public static final Type ROAD_1 = new Type("/images/tiles/road/road_1.png");
    @Nonnull
    public static final Type ROAD_2 = new Type("/images/tiles/road/road_2.png");
    @Nonnull
    public static final Type ROAD_3 = new Type("/images/tiles/road/road_3.png");
    @Nonnull
    public static final Type ROAD_4 = new Type("/images/tiles/road/road_4.png");
    @Nonnull
    public static final Type ROAD_5 = new Type("/images/tiles/road/road_5.png");
    @Nonnull
    public static final Type ROAD_6 = new Type("/images/tiles/road/road_6.png");
    @Nonnull
    public static final Type ROAD_7 = new Type("/images/tiles/road/road_7.png");
    @Nonnull
    public static final Type ROAD_8 = new Type("/images/tiles/road/road_8.png");
    @Nonnull
    public static final Type ROAD_9 = new Type("/images/tiles/road/road_9.png");
    @Nonnull
    public static final Type ROAD_10 = new Type("/images/tiles/road/road_10.png");
    @Nonnull
    public static final Type ROAD_11 = new Type("/images/tiles/road/road_11.png");
    @Nonnull
    public static final Type ROAD_12 = new Type("/images/tiles/road/road_12.png");

    @Nonnull
    public static final Type DIRT = new Type("/images/tiles/grass/dirt.png");
    @Nonnull
    public static final Type WALL = new Type("/images/tiles/objects/wall.png", true);
    @Nonnull
    public static final Type TREE = new Type("/images/tiles/objects/tree.png", true);

    @Nonnull
    public static final Type HUT = new Type("/images/tiles/objects/hut.png", true);
    @Nonnull
    public static final Type FLOOR = new Type("/images/tiles/objects/floor.png");
    @Nonnull
    public static final Type TABLE = new Type("/images/tiles/objects/table.png", true);
    @Nonnull
    public static final Type DOOR = new Type("/images/tiles/objects/door.png", true);
    @Nonnull
    public static final Type CHEST_CLOSED = new Type("/images/tiles/objects/chest/closed.png", true);
    @Nonnull
    public static final Type CHEST_OPENED = new Type("/images/tiles/objects/chest/opened.png", true);

    @Nonnull
    public static final Type PLAYER_UP_1 = new Type(-1, "/images/entity/player/player_up_1.png");
    @Nonnull
    public static final Type PLAYER_UP_2 = new Type(-2, "/images/entity/player/player_up_2.png");
    @Nonnull
    public static final Type PLAYER_DOWN_1 = new Type(-3, "/images/entity/player/player_down_1.png");
    @Nonnull
    public static final Type PLAYER_DOWN_2 = new Type(-4, "/images/entity/player/player_down_2.png");
    @Nonnull
    public static final Type PLAYER_LEFT_1 = new Type(-5, "/images/entity/player/player_left_1.png");
    @Nonnull
    public static final Type PLAYER_LEFT_2 = new Type(-6, "/images/entity/player/player_left_2.png");
    @Nonnull
    public static final Type PLAYER_RIGHT_1 = new Type(-7, "/images/entity/player/player_right_1.png");
    @Nonnull
    public static final Type PLAYER_RIGHT_2 = new Type(-8, "/images/entity/player/player_right_2.png");

    @Nonnull
    private final String file;
    @Nullable
    private Image image;
    private final boolean collidable;
    private final int id;

    Type(int id, @Nonnull String file, boolean collidable) {
        if (values.containsKey(id)) throw new IllegalArgumentException("the id %s is already taken".formatted(id));
        this.collidable = collidable;
        this.file = file;
        this.id = id;
        values.put(id, this);
    }

    Type(@Nonnull String file, boolean collidable) {
        this(ID++, file, collidable);
    }

    Type(int id, @Nonnull String file) {
        this(id, file, false);
    }

    Type(@Nonnull String file) {
        this(ID++, file);
    }

    @Nonnull
    public static Type valueOf(int id) {
        return values.get(id);
    }

    public static boolean exists(int id) {
        return values.containsKey(id);
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

    @Override
    public String toString() {
        return "Type{" +
                "file='" + file + '\'' +
                ", collidable=" + collidable +
                ", id=" + id +
                '}';
    }
}
