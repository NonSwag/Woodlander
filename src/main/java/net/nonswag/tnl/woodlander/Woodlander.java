package net.nonswag.tnl.woodlander;

import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.images.Images;
import net.nonswag.tnl.woodlander.world.worlds.Overworld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.image.BufferedImage;
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
    public static final GamePanel GAME_PANEL;
    public static int FPS = 60;
    public static boolean STOPPING = false;

    static {
        WORLDS.add(new Overworld());
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
        WINDOW.setIconImage(Images.PLAYER_DOWN_1.getImage().getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH));
        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        WINDOW.setMinimumSize(GAME_PANEL.getMinimumSize());
        WINDOW.setContentPane(GAME_PANEL);
        // WINDOW.setResizable(false);
        WINDOW.pack();
        WINDOW.setLocationRelativeTo(null);
        WINDOW.setVisible(true);
    }
}