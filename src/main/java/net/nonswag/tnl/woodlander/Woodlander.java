package net.nonswag.tnl.woodlander;

import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.entities.Entity;
import net.nonswag.tnl.woodlander.world.worlds.Overworld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static final List<Entity> ENTITIES = new ArrayList<>(){
        @Override
        public boolean add(@Nullable Entity entity) {
            if (entity == null) throw new IllegalArgumentException("entity cannot be null");
            if (contains(entity)) throw new IllegalArgumentException("an entity with this id is already registered");
            return super.add(entity);
        }
    };
    @Nonnull
    public static final GamePanel GAME_PANEL;
    public static int WIDTH = 1080;
    public static int HEIGHT = 720;
    public static int FPS = 60;
    public static boolean STOPPING = false;

    static {
        try {
            WORLDS.add(new Overworld());
            GAME_PANEL = new GamePanel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        WINDOW.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        WINDOW.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT / 2));
        WINDOW.setContentPane(GAME_PANEL);
        WINDOW.pack();
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
    }
}