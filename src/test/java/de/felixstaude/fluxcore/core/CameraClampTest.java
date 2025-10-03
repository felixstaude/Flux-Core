package de.felixstaude.fluxcore.core;

import de.felixstaude.fluxcore.util.Mathx;
import de.felixstaude.fluxcore.world.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CameraClampTest {
    @Test
    void cameraClamp_withViewport1720x880_producesCorrectBounds() {
        float viewportW = 1720f;
        float viewportH = 880f;
        float halfW = viewportW * 0.5f;  // 860
        float halfH = viewportH * 0.5f;  // 440

        assertEquals(860f, halfW, 0.01f);
        assertEquals(440f, halfH, 0.01f);

        // Player at center -> camera centered
        float playerX = Constants.ARENA_W / 2f;  // 1200
        float targetX = Mathx.clamp(playerX, halfW, Constants.ARENA_W - halfW);
        assertEquals(1200f, targetX, 0.01f);

        // Player at left edge
        playerX = 100f;
        targetX = Mathx.clamp(playerX, halfW, Constants.ARENA_W - halfW);
        assertEquals(860f, targetX, 0.01f);  // Clamped to min

        // Player at right edge
        playerX = 2300f;
        targetX = Mathx.clamp(playerX, halfW, Constants.ARENA_W - halfW);
        assertEquals(1540f, targetX, 0.01f);  // Clamped to max (2400 - 860)
    }

    @Test
    void cameraClamp_Y_axis_withViewport880_producesCorrectBounds() {
        float viewportH = 880f;
        float halfH = viewportH * 0.5f;  // 440

        // Player at center
        float playerY = Constants.ARENA_H / 2f;  // 800
        float targetY = Mathx.clamp(playerY, halfH, Constants.ARENA_H - halfH);
        assertEquals(800f, targetY, 0.01f);

        // Player at bottom edge
        playerY = 100f;
        targetY = Mathx.clamp(playerY, halfH, Constants.ARENA_H - halfH);
        assertEquals(440f, targetY, 0.01f);  // Clamped to min

        // Player at top edge
        playerY = 1500f;
        targetY = Mathx.clamp(playerY, halfH, Constants.ARENA_H - halfH);
        assertEquals(1160f, targetY, 0.01f);  // Clamped to max (1600 - 440)
    }
}
