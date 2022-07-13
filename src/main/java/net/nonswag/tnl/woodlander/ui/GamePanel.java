package net.nonswag.tnl.woodlander.ui;

import lombok.Getter;
import net.nonswag.tnl.woodlander.GameLoop;
import net.nonswag.tnl.woodlander.Woodlander;
import net.nonswag.tnl.woodlander.world.Location;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.entities.Entity;
import net.nonswag.tnl.woodlander.world.entities.Player;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter
public class GamePanel extends JPanel implements Runnable {

    private static final int originalTileSize = 16;
    private static final int scale = 3;

    public static final int TILE_SIZE = originalTileSize * scale;
    public static boolean PAUSE = false;

    @Nonnull
    private final GameLoop gameLoop = new GameLoop(this);
    @Nonnull
    private final Player player = new Player(new Location(Woodlander.WORLDS.get(0), 0, 0));

    public GamePanel() {
        setPreferredSize(new Dimension(22 * TILE_SIZE, 13 * TILE_SIZE));
        setMinimumSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height / 2));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(@Nonnull KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) PAUSE = !PAUSE;
                else if (PAUSE) event.consume();
            }
        });
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
        for (Entity entity : world.getEntities()) if (entity.isOnScreen()) entity.render(graphic);
        if (PAUSE) {
            graphic.setColor(Color.WHITE);
            graphic.setFont(Woodlander.FONT.deriveFont(60f));
            graphic.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphic.drawString("PAUSE", getScreenWidth() / 2, getScreenHeight() / 2);
        }
        graphic.dispose();
    }

    @Override
    public void run() {
        if (!PAUSE) for (Entity entity : getPlayer().getWorld().getEntities()) entity.tick();
        repaint();
    }

    public static boolean isOnScreen(int x, int y) {
        return x + GamePanel.TILE_SIZE >= 0 && y + GamePanel.TILE_SIZE >= 0 && x < GamePanel.getScreenWidth() && y < GamePanel.getScreenHeight();
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
