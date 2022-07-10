package net.nonswag.tnl.woodlander;

import lombok.SneakyThrows;

import javax.annotation.Nonnull;

public class GameLoop extends Thread {

    @Nonnull
    private final Runnable tick;

    public GameLoop(@Nonnull Runnable tick) {
        this.tick = tick;
    }

    @Override
    @SneakyThrows
    public void run() {
        while (!Woodlander.STOPPING) {
            tick.run();
            Thread.sleep(1000L / Woodlander.FPS);
        }
    }
}
