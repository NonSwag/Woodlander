package net.nonswag.tnl.woodlander.world.entities;

import net.nonswag.tnl.woodlander.world.Location;

import javax.annotation.Nonnull;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player extends Entity implements KeyListener {

    public Player(@Nonnull Location location) throws IOException {
        super(location, "/images/entity/player/", "player_down_1.png", "player_down_2.png", "player_up_1.png", "player_up_2.png",
                "player_right_1.png", "player_right_2.png", "player_left_1.png", "player_left_2.png");
    }

    @Override
    public void keyTyped(@Nonnull KeyEvent event) {
    }

    @Override
    public void keyPressed(@Nonnull KeyEvent event) {
    }

    @Override
    public void keyReleased(@Nonnull KeyEvent event) {
    }
}
