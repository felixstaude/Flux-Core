package de.felixstaude.fluxcore.core;

import de.felixstaude.fluxcore.world.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewportMarginTest {
    @Test
    void viewport_1920x1080_withMargin100_produces1720x880() {
        int screenW = 1920;
        int screenH = 1080;
        int mx = Constants.SCREEN_MARGIN;
        int my = Constants.SCREEN_MARGIN;

        int viewportW = screenW - 2 * mx;
        int viewportH = screenH - 2 * my;

        assertEquals(1720, viewportW);
        assertEquals(880, viewportH);
    }

    @Test
    void viewport_1280x800_withMargin100_produces1080x600() {
        int screenW = 1280;
        int screenH = 800;
        int mx = Constants.SCREEN_MARGIN;
        int my = Constants.SCREEN_MARGIN;

        int viewportW = screenW - 2 * mx;
        int viewportH = screenH - 2 * my;

        assertEquals(1080, viewportW);
        assertEquals(600, viewportH);
    }
}
