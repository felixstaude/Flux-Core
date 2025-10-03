package de.felixstaude.fluxcore.world;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViewportMathTest {
    @Test
    void gridMinor_produces49x33Lines_for2400x1600() {
        int expectedMinorX = (int) (Constants.ARENA_W / Constants.GRID_MINOR) + 1; // 2400/50 + 1 = 49
        int expectedMinorY = (int) (Constants.ARENA_H / Constants.GRID_MINOR) + 1; // 1600/50 + 1 = 33

        assertEquals(49, expectedMinorX);
        assertEquals(33, expectedMinorY);
    }

    @Test
    void gridMajor_produces10x7Lines_for2400x1600() {
        int expectedMajorX = (int) (Constants.ARENA_W / Constants.GRID_MAJOR) + 1; // 2400/250 + 1 = 10
        int expectedMajorY = (int) (Constants.ARENA_H / Constants.GRID_MAJOR) + 1; // 1600/250 + 1 = 7

        assertEquals(10, expectedMajorX);
        assertEquals(7, expectedMajorY);
    }

    @Test
    void arenaConstants_matchExpected() {
        assertEquals(2400f, Constants.ARENA_W);
        assertEquals(1600f, Constants.ARENA_H);
    }

    @Test
    void gridConstants_matchExpected() {
        assertEquals(50, Constants.GRID_MINOR);
        assertEquals(250, Constants.GRID_MAJOR);
    }
}
