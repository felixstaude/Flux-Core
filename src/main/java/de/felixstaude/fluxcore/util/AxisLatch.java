package de.felixstaude.fluxcore.util;

/**
 * First-down-wins axis latch.
 * Pressing opposite key while first is held does NOT cancel or reverse.
 */
public class AxisLatch {
    private int state = 0; // -1 = negative held, 0 = none, +1 = positive held

    /**
     * Update latch state.
     * @param negHeld true if negative key (A/S) is held
     * @param posHeld true if positive key (D/W) is held
     * @return -1, 0, or +1
     */
    public int update(boolean negHeld, boolean posHeld) {
        if (state == 0) {
            // No key latched - latch first pressed
            if (negHeld && !posHeld) {
                state = -1;
            } else if (posHeld && !negHeld) {
                state = +1;
            }
        } else if (state == -1) {
            // Negative latched - release only if negative released
            if (!negHeld && posHeld) {
                state = +1; // Hand-over to positive
            } else if (!negHeld && !posHeld) {
                state = 0;
            }
        } else if (state == +1) {
            // Positive latched - release only if positive released
            if (!posHeld && negHeld) {
                state = -1; // Hand-over to negative
            } else if (!posHeld && !negHeld) {
                state = 0;
            }
        }
        return state;
    }

    public int getState() {
        return state;
    }

    public void reset() {
        state = 0;
    }
}
