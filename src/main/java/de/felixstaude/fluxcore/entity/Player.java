package de.felixstaude.fluxcore.entity;

import de.felixstaude.fluxcore.world.Constants;

public class Player {
    public float x;
    public float y;
    public float vx = 0f;
    public float vy = 0f;
    public float radius = 12f;
    public float maxSpeed = Constants.MOVE_MAX_SPEED;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
