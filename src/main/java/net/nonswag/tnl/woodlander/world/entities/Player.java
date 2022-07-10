package net.nonswag.tnl.woodlander.entities;

import javax.annotation.Nonnull;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player extends Entity implements KeyListener {

    public Player() throws IOException {
        super("/images/entity/player/", "player_down_1.png", "player_down_2.png", "player_up_1.png", "player_up_2.png",
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
