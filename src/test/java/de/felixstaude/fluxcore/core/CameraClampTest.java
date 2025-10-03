package de.felixstaude.fluxcore.core;

import com.badlogic.gdx.math.MathUtils;
import de.felixstaude.fluxcore.world.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CameraFollowTest {
    @Test
    void cameraClamp_atOrigin_clampsToHalfViewport() {
        // Simulate viewport dimensions
        float viewportWidth = 1720f;
        float viewportHeight = 880f;
        float halfW = viewportWidth * 0.5f;  // 860
        float halfH = viewportHeight * 0.5f;  // 440

        // Player at (0, 0)
        float playerX = 0f;
        float playerY = 0f;

        // Camera follows player
        float camX = playerX;
        float camY = playerY;

        // Clamp to arena
        camX = MathUtils.clamp(camX, halfW, Constants.ARENA_W - halfW);
        camY = MathUtils.clamp(camY, halfH, Constants.ARENA_H - halfH);

        // Should be clamped to halfW/halfH
        assertEquals(860f, camX, 0.01f);
        assertEquals(440f, camY, 0.01f);
    }

    @Test
    void cameraClamp_atArenaMax_clampsToArenaMinusHalfViewport() {
        // Simulate viewport dimensions
        float viewportWidth = 1720f;
        float viewportHeight = 880f;
        float halfW = viewportWidth * 0.5f;  // 860
        float halfH = viewportHeight * 0.5f;  // 440

        // Player at arena max
        float playerX = Constants.ARENA_W;  // 2400
        float playerY = Constants.ARENA_H;  // 1600

        // Camera follows player
        float camX = playerX;
        float camY = playerY;

        // Clamp to arena
        camX = MathUtils.clamp(camX, halfW, Constants.ARENA_W - halfW);
        camY = MathUtils.clamp(camY, halfH, Constants.ARENA_H - halfH);

        // Should be clamped to ARENA - halfW/halfH
        assertEquals(1540f, camX, 0.01f);  // 2400 - 860
        assertEquals(1160f, camY, 0.01f);  // 1600 - 440
    }
}
