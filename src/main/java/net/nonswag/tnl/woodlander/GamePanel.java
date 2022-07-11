package net.nonswag.tnl.woodlander;

import lombok.Getter;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.entities.Entity;
import net.nonswag.tnl.woodlander.world.entities.Player;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;

@Getter
public class GamePanel extends JPanel implements Runnable {

    private static final int originalTileSize = 16;
    private static final int scale = 3;

    public static final int TILE_SIZE = originalTileSize * scale;
    public static final int MAX_SCREEN_COLUMNS = 22;
    public static final int MAX_SCREEN_ROWS = 13;

    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;

    @Nonnull
    private final GameLoop gameLoop = new GameLoop(this);
    @Nonnull
    private final Player player = new Player(Woodlander.WORLDS.get(0).center());

    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setMinimumSize(new Dimension(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2));
        requestFocus(FocusEvent.Cause.ACTIVATION);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(player);
        setFocusable(true);
        gameLoop.start();
    }

    @Override
    public void paint(@Nonnull Graphics graphics) {
        super.paint(graphics);
        if (!(graphics instanceof Graphics2D graphic)) return;
        World world = getPlayer().getWorld();
        world.getMap().render(getPlayer().getLocation(), graphic);
        for (Entity entity : world.getEntities()) entity.render(graphic);
        graphic.dispose();
    }

    @Override
    public void run() {
        for (Entity entity : getPlayer().getWorld().getEntities()) entity.tick();
        repaint();
    }
}
