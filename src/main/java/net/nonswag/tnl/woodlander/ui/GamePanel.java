package net.nonswag.tnl.woodlander.ui;

import lombok.Getter;
import net.nonswag.tnl.woodlander.GameLoop;
import net.nonswag.tnl.woodlander.Woodlander;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.entities.Entity;
import net.nonswag.tnl.woodlander.world.entities.Player;
import net.nonswag.tnl.woodlander.world.images.Type;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class GamePanel extends JPanel implements Runnable {

    private static final int originalTileSize = 16;
    private static final int scale = 3;

    public static final int TILE_SIZE = originalTileSize * scale;
    public static boolean PAUSE = false;

    @Nonnull
    private final GameLoop gameLoop = new GameLoop(this);
    @Nonnull
    private final Player player = new Player(Woodlander.WORLDS.get(0).getSpawnLocation());

    public GamePanel() {
        setPreferredSize(new Dimension(22 * TILE_SIZE, 13 * TILE_SIZE));
        setMinimumSize(new Dimension(getPreferredSize().width / 2, getPreferredSize().height / 2));
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(@Nonnull KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_F11) {
                    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    if (environment.getDefaultScreenDevice().getFullScreenWindow() == null) {
                        environment.getDefaultScreenDevice().setFullScreenWindow(Woodlander.WINDOW);
                    } else environment.getDefaultScreenDevice().setFullScreenWindow(null);
                }
            }

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

        player.setCollisionEvent(location -> {
            if (location.getWorld().getName().equals("Overworld") && location.getTile().getType().equals(Type.HUT)) {
                World world = Woodlander.getWorld("Test");
                if (world != null) player.teleport(world.getSpawnLocation());
            }
        });
    }

    @Override
    public void paint(@Nonnull Graphics graphics) {
        try {
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
                Woodlander.WINDOW.setCursor(Cursor.getDefaultCursor());
            } else Woodlander.WINDOW.setCursor(CursorManager.getInvisibleCursor());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            graphics.dispose();
        }
    }

    @Override
    public void run() {
        if (!PAUSE) {
            List<Entity> entities = getPlayer().getWorld().getEntities();
            IntStream.range(0, entities.size()).forEach(i -> entities.get(i).tick());
        }
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
