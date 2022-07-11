package net.nonswag.tnl.woodlander.world.map;

import javax.annotation.Nonnull;

public class MapParseException extends RuntimeException {

    public MapParseException() {
    }

    public MapParseException(@Nonnull String message) {
        super(message);
    }

    public MapParseException(@Nonnull String message, @Nonnull Throwable cause) {
        super(message, cause);
    }

    public MapParseException(@Nonnull Throwable cause) {
        super(cause);
    }
}
