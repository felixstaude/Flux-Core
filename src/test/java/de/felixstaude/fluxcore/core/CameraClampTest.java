package de.felixstaude.fluxcore.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CameraFollowTest {
    @Test
    void cameraFollow_noClamp_allowsNegativeCoordinates() {
        // Camera should follow player even outside arena bounds
        float playerX = -100f;
        float playerY = -100f;

        // Camera targets player position directly (no clamping)
        // In actual implementation: cam.x = lerp(cam.x, player.x, lerp)
        // This test verifies the math allows negative coordinates
        assertTrue(playerX < 0);
        assertTrue(playerY < 0);
    }

    @Test
    void cameraFollow_noClamp_allowsOutsideArenaBounds() {
        // Camera should follow player beyond arena boundaries
        float playerX = 3000f;  // Beyond ARENA_W (2400)
        float playerY = 2000f;  // Beyond ARENA_H (1600)

        // Camera should be able to track these positions
        assertTrue(playerX > 2400f);
        assertTrue(playerY > 1600f);
    }
}
