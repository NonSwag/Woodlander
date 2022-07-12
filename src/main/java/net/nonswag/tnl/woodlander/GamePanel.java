package net.nonswag.tnl.woodlander;

import lombok.Getter;
import net.nonswag.tnl.woodlander.world.Location;
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

    @Nonnull
    private final GameLoop gameLoop = new GameLoop(this);
    @Nonnull
    private final Player player = new Player(new Location(Woodlander.WORLDS.get(0), 0, 0));

    public GamePanel() {
        setPreferredSize(new Dimension(22 * TILE_SIZE, 13 * TILE_SIZE));
        setMinimumSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height / 2));
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

    public static int getMaxScreenColumns() {
        return getScreenWidth() / GamePanel.TILE_SIZE;
    }

    public static int getMaxScreenRows() {
        return getScreenHeight() / GamePanel.TILE_SIZE;
    }

    public static int getScreenWidth() {
        return Woodlander.WINDOW.getWidth();
    }

    public static int getScreenHeight() {
        return Woodlander.WINDOW.getHeight();
    }
}
