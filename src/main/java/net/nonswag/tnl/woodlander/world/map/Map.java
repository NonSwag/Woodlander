package net.nonswag.tnl.woodlander.world.map;

import lombok.Getter;
import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.entities.Player;
import net.nonswag.tnl.woodlander.world.images.Images;
import net.nonswag.tnl.woodlander.world.tiles.Tile;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Getter
public class Map {

    @Nonnull
    private final Tile[][] tiles;
    @Nonnull
    private final String resource;

    public Map(@Nonnull String resource) throws MapParseException {
        this.tiles = parse(this.resource = resource);
    }

    @Nonnull
    private Tile[][] parse(@Nonnull String resource) throws MapParseException {
        try (InputStream stream = getClass().getResourceAsStream(resource)) {
            if (stream == null) return new Tile[0][];
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            List<String> lines = reader.lines().toList();
            Tile[][] tiles = new Tile[lines.size()][];
            for (int i = 0; i < lines.size(); i++) tiles[i] = parse(i + 1, lines.get(i).split(" "));
            return tiles;
        } catch (IOException e) {
            throw new MapParseException(e);
        }
    }

    @Nonnull
    private Tile[] parse(int line, @Nonnull String[] strings) throws MapParseException {
        Tile[] tiles = new Tile[strings.length];
        for (int i = 0; i < strings.length; i++) {
            try {
                int id = Integer.parseInt(strings[i]);
                if (id >= 0 && id < Images.count()) tiles[i] = new Tile(id);
                else throw new MapParseException("Found no image with id %s".formatted(id));
            } catch (NumberFormatException e) {
                throw new MapParseException("Error in line %s at column %s in file %s".formatted(line, i * 2 + 1, resource), e);
            }
        }
        return tiles;
    }

    public void render(@Nonnull Location location, @Nonnull Graphics2D graphic) {
        for (int row = 0; row < tiles.length; row++) {
            int size = GamePanel.TILE_SIZE;
            int screenY = (row * size) - location.getY() + Player.getScreenY();
            for (int col = 0; col < tiles[row].length; col++) {
                Tile tile = tiles[row][col];
                int screenX = (col * size) - location.getX() + Player.getScreenX();
                if (!GamePanel.isOnScreen(screenX, screenY)) continue;
                graphic.drawImage(tile.getImage(), screenX, screenY, null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Arrays.deepEquals(tiles, map.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    @Override
    public String toString() {
        return "Map{" +
                "resource='" + resource + '\'' +
                '}';
    }
}
