package de.felixstaude.fluxcore.util;

import com.badlogic.gdx.graphics.Color;

public class Palette {
    public static Color rgba(int r, int g, int b, float a) {
        return new Color(r / 255f, g / 255f, b / 255f, a);
    }

    public static final Color BACKDROP  = rgba(0x0B, 0x12, 0x20, 1f);
    public static final Color GRID_MINOR= rgba(0x15, 0x26, 0x3C, 0.35f);
    public static final Color GRID_MAJOR= rgba(0x1F, 0x3A, 0x5C, 0.60f);
    public static final Color ARENA_BORDER = new Color(0f, 170f / 255f, 220f / 255f, 1f);
}
