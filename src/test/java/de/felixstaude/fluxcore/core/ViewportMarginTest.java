package de.felixstaude.fluxcore.core;

import de.felixstaude.fluxcore.world.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewportMarginTest {
    @Test
    void viewport_1920x1080_withMargin100_produces1720x880() {
        int screenW = 1920;
        int screenH = 1080;
        int m = Constants.SCREEN_MARGIN;

        int viewportW = screenW - 2 * m;
        int viewportH = screenH - 2 * m;
        int sx = m;
        int sy = m;

        assertEquals(1720, viewportW);
        assertEquals(880, viewportH);
        assertEquals(100, sx);
        assertEquals(100, sy);
    }

    @Test
    void viewport_1280x800_withMargin100_produces1080x600() {
        int screenW = 1280;
        int screenH = 800;
        int m = Constants.SCREEN_MARGIN;

        int viewportW = screenW - 2 * m;
        int viewportH = screenH - 2 * m;

        assertEquals(1080, viewportW);
        assertEquals(600, viewportH);
    }

    @Test
    void gridSnapping_minor50_snapsToMultiples() {
        float left = -123.5f;
        float bottom = 78.3f;
        int stepMinor = 50;

        float sxMinor = (float) Math.floor(left / stepMinor) * stepMinor;
        float syMinor = (float) Math.floor(bottom / stepMinor) * stepMinor;

        assertEquals(-150f, sxMinor, 0.01f);
        assertEquals(50f, syMinor, 0.01f);
    }

    @Test
    void gridSnapping_major250_snapsToMultiples() {
        float left = -456.7f;
        float bottom = 389.2f;
        int stepMajor = 250;

        float sxMajor = (float) Math.floor(left / stepMajor) * stepMajor;
        float syMajor = (float) Math.floor(bottom / stepMajor) * stepMajor;

        assertEquals(-500f, sxMajor, 0.01f);
        assertEquals(250f, syMajor, 0.01f);
    }
}
