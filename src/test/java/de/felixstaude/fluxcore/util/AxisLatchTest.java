package de.felixstaude.fluxcore.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AxisLatchTest {
    @Test
    void initialState_isZero() {
        AxisLatch latch = new AxisLatch();
        assertEquals(0, latch.getState());
    }

    @Test
    void pressingNegative_latchesNegative() {
        AxisLatch latch = new AxisLatch();
        int result = latch.update(true, false);
        assertEquals(-1, result);
    }

    @Test
    void pressingPositive_latchesPositive() {
        AxisLatch latch = new AxisLatch();
        int result = latch.update(false, true);
        assertEquals(1, result);
    }

    @Test
    void pressingBoth_doesNotLatch() {
        AxisLatch latch = new AxisLatch();
        int result = latch.update(true, true);
        assertEquals(0, result);
    }

    @Test
    void negativeLatched_pressingOpposite_keepsNegative() {
        AxisLatch latch = new AxisLatch();
        latch.update(true, false);  // Latch negative
        int result = latch.update(true, true);  // Press both
        assertEquals(-1, result);  // Still negative
    }

    @Test
    void positiveLatched_pressingOpposite_keepsPositive() {
        AxisLatch latch = new AxisLatch();
        latch.update(false, true);  // Latch positive
        int result = latch.update(true, true);  // Press both
        assertEquals(1, result);  // Still positive
    }

    @Test
    void negativeLatched_releaseNegative_releasesLatch() {
        AxisLatch latch = new AxisLatch();
        latch.update(true, false);  // Latch negative
        int result = latch.update(false, false);  // Release all
        assertEquals(0, result);
    }

    @Test
    void positiveLatched_releasePositive_releasesLatch() {
        AxisLatch latch = new AxisLatch();
        latch.update(false, true);  // Latch positive
        int result = latch.update(false, false);  // Release all
        assertEquals(0, result);
    }

    @Test
    void negativeLatched_releaseNegativeWhilePositiveHeld_switchesToPositive() {
        AxisLatch latch = new AxisLatch();
        latch.update(true, false);  // Latch negative
        int result = latch.update(false, true);  // Release negative, keep positive
        assertEquals(1, result);  // Hand-over to positive
    }

    @Test
    void positiveLatched_releasePositiveWhileNegativeHeld_switchesToNegative() {
        AxisLatch latch = new AxisLatch();
        latch.update(false, true);  // Latch positive
        int result = latch.update(true, false);  // Release positive, keep negative
        assertEquals(-1, result);  // Hand-over to negative
    }

    @Test
    void reset_clearsState() {
        AxisLatch latch = new AxisLatch();
        latch.update(false, true);  // Latch positive
        latch.reset();
        assertEquals(0, latch.getState());
    }
}
