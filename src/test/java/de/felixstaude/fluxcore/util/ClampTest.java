package de.felixstaude.fluxcore.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClampTest {
    @Test
    void clampFloat_withinRange_returnsValue() {
        assertEquals(5.0f, Mathx.clamp(5.0f, 0.0f, 10.0f));
    }

    @Test
    void clampFloat_belowMin_returnsMin() {
        assertEquals(0.0f, Mathx.clamp(-5.0f, 0.0f, 10.0f));
    }

    @Test
    void clampFloat_aboveMax_returnsMax() {
        assertEquals(10.0f, Mathx.clamp(15.0f, 0.0f, 10.0f));
    }

    @Test
    void clampInt_withinRange_returnsValue() {
        assertEquals(5, Mathx.clamp(5, 0, 10));
    }

    @Test
    void clampInt_belowMin_returnsMin() {
        assertEquals(0, Mathx.clamp(-5, 0, 10));
    }

    @Test
    void clampInt_aboveMax_returnsMax() {
        assertEquals(10, Mathx.clamp(15, 0, 10));
    }

    @Test
    void clampDouble_withinRange_returnsValue() {
        assertEquals(5.0, Mathx.clamp(5.0, 0.0, 10.0));
    }

    @Test
    void clampDouble_belowMin_returnsMin() {
        assertEquals(0.0, Mathx.clamp(-5.0, 0.0, 10.0));
    }

    @Test
    void clampDouble_aboveMax_returnsMax() {
        assertEquals(10.0, Mathx.clamp(15.0, 0.0, 10.0));
    }
}
