package net.nonswag.tnl.woodlander.ui;

import lombok.Getter;

import javax.annotation.Nonnull;
import java.awt.*;

public final class CursorManager {

    @Getter
    @Nonnull
    private static final Cursor invisibleCursor;

    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("/cursors/invisible.png");
        invisibleCursor = toolkit.createCustomCursor(image , new Point(0, 0), "");
    }
}
