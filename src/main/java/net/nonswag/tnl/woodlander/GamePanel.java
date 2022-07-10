package net.nonswag.tnl.woodlander;

import lombok.Getter;
import net.nonswag.tnl.woodlander.world.World;
import net.nonswag.tnl.woodlander.world.entities.Entity;
import net.nonswag.tnl.woodlander.world.entities.Player;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.io.IOException;

@Getter
public class GamePanel extends JPanel implements Runnable {

    @Nonnull
    private final GameLoop gameLoop = new GameLoop(this);
    @Nonnull
    private final Player player = new Player(Woodlander.WORLDS.get(0).center());

    public GamePanel() throws IOException {
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
        world.getMap().paint(getPlayer().getLocation(), graphic);
        for (Entity entity : world.getEntities()) entity.paint(graphic);
        graphic.dispose();
    }

    @Override
    public void run() {
        for (Entity entity : getPlayer().getWorld().getEntities()) entity.tick();
        repaint();
    }
}
