package net.nonswag.tnl.woodlander;

import net.nonswag.tnl.woodlander.ui.CursorManager;
import net.nonswag.tnl.woodlander.ui.GamePanel;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.images.Type;
import net.nonswag.tnl.woodlander.world.worlds.Overworld;
import net.nonswag.tnl.woodlander.world.worlds.Test;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class Woodlander {

    @Nonnull
    public static final JFrame WINDOW = new JFrame("Woodlander 2D");

    @Nonnull
    public static final List<World> WORLDS = new ArrayList<>() {
        @Override
        public boolean add(@Nullable World world) {
            if (world == null) throw new IllegalArgumentException("world cannot be null");
            if (contains(world)) throw new IllegalArgumentException("a world with this name is already registered");
            return super.add(world);
        }
    };
    @Nonnull
    public static final GamePanel GAME_PANEL;
    @Nonnull
    public static final Font FONT;
    public static int FPS = 60;
    public static boolean STOPPING = false;

    static {
        String font = "/fonts/minecraft.ttf";
        try (InputStream stream = Woodlander.class.getResourceAsStream(font)) {
            if (stream == null) throw new MissingResourceException(font, Woodlander.class.getName(), font);
            FONT = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        WORLDS.add(new Overworld());
        WORLDS.add(new Test());
        GAME_PANEL = new GamePanel();
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                STOPPING = true;
                GAME_PANEL.getGameLoop().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "shutdown-executor"));
        initWindow();
    }

    private static void initWindow() {
        WINDOW.setIconImage(Type.PLAYER_DOWN_1.getImage().getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH));
        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        WINDOW.setMinimumSize(GAME_PANEL.getMinimumSize());
        WINDOW.setContentPane(GAME_PANEL);
        WINDOW.pack();
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
        WINDOW.setCursor(CursorManager.getInvisibleCursor());
    }

    @Nullable
    public static World getWorld(@Nonnull String name) {
        for (World world : WORLDS) if (world.getName().equals(name)) return world;
        for (World world : WORLDS) {
            if (!world.getName().equalsIgnoreCase(name)) continue;
            System.err.printf("use the world name %s", name);
            return world;
        }
        return null;
    }
}