package net.nonswag.tnl.woodlander.world.tiles;

import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
public class Tile {

    private final int id;
    private final double width = 48;
    private final double height = 48;

    public Tile(int id) {
        this.id = id;
    }

    public enum Type {
        GRASS("/images/tiles/grass/", new Mapping[]{Mapping.DIRT, Mapping.GRASS_0, Mapping.GRASS_1}),
        OBJECT("/images/tiles/objects/", new Mapping[]{}),
        ROAD("/images/tiles/road/", new Mapping[]{}),
        WATER("/images/tiles/water/", new Mapping[]{});

        @Nonnull
        private final String path;

        Type(@Nonnull String path, @Nonnull Mapping[] mappings) {
            this.path = path;
        }

        public enum Mapping {
            DIRT("dirt.png"),
            GRASS_0("grass_0.png"),
            GRASS_1("grass_1.png"),

            FLOOR("floor.png"),
            HUT("hut.png"),
            TABLE("table.png"),
            TREE("tree.png"),
            ;

            @Nonnull
            private final String file;

            Mapping(@Nonnull String file) {
                this.file = file;
            }
        }
    }
}
