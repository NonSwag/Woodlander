package net.nonswag.tnl.woodlander.world;

import lombok.Getter;
import net.nonswag.tnl.woodlander.Woodlander;
import net.nonswag.tnl.woodlander.world.tiles.Tile;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Getter
public class Map {

    @Nonnull
    private final Tile[][] tiles;

    public Map(@Nonnull String resource) throws IOException {
        this.tiles = parse(resource);
    }

    @Nonnull
    private Tile[][] parse(@Nonnull String resource) throws IOException {
        try (InputStream stream = Woodlander.class.getResourceAsStream(resource)) {
            if (stream == null) return new Tile[0][];
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            List<String> lines = reader.lines().toList();
            Tile[][] tiles = new Tile[lines.size()][];
            for (int i = 0; i < lines.size(); i++) tiles[i] = parse(lines.get(i).split(" "));
            return tiles;
        }
    }

    @Nonnull
    private Tile[] parse(@Nonnull String[] strings) throws NumberFormatException {
        Tile[] tiles = new Tile[strings.length];
        for (int i = 0; i < strings.length; i++) tiles[i] = new Tile(Integer.parseInt(strings[i]));
        return tiles;
    }

    public void paint(@Nonnull Location location, @Nonnull Graphics2D graphic) {
    }
}
