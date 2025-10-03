package de.felixstaude.fluxcore.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import de.felixstaude.fluxcore.core.RenderContext;
import de.felixstaude.fluxcore.entity.Player;
import de.felixstaude.fluxcore.util.AxisLatch;
import de.felixstaude.fluxcore.util.Mathx;
import de.felixstaude.fluxcore.util.Palette;
import de.felixstaude.fluxcore.world.Constants;

public class FluxCore extends ApplicationAdapter {
    private RenderContext rc;
    private Player player;
    private AxisLatch xLatch = new AxisLatch();
    private AxisLatch yLatch = new AxisLatch();

    @Override
    public void create() {
        rc = new RenderContext();
        player = new Player(Constants.ARENA_W / 2f, Constants.ARENA_H / 2f);
        rc.worldCam.position.set(player.x, player.y, 0);
        rc.worldCam.update();
    }

    @Override
    public void render() {
        // ESC → quit
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // Clear
        Gdx.gl.glClearColor(Palette.BACKDROP.r, Palette.BACKDROP.g, Palette.BACKDROP.b, Palette.BACKDROP.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float dt = Gdx.graphics.getDeltaTime();

        // Input with axis latch (first-down-wins, non-cancelable)
        boolean aHeld = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean dHeld = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean wHeld = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sHeld = Gdx.input.isKeyPressed(Input.Keys.S);

        int xDir = xLatch.update(aHeld, dHeld);
        int yDir = yLatch.update(sHeld, wHeld);

        // Desired input vector (normalized if diagonal)
        float dx = xDir;
        float dy = yDir;
        if (dx != 0 && dy != 0) {
            float len = (float) Math.sqrt(dx * dx + dy * dy);
            dx /= len;
            dy /= len;
        }

        // Target velocity
        float targetVx = dx * Constants.MOVE_MAX_SPEED;
        float targetVy = dy * Constants.MOVE_MAX_SPEED;

        // Accelerate/decelerate towards target
        player.vx = accelerateTowards(player.vx, targetVx, dt);
        player.vy = accelerateTowards(player.vy, targetVy, dt);

        // Integrate position
        player.x += player.vx * dt;
        player.y += player.vy * dt;

        // Clamp to arena
        player.x = Mathx.clamp(player.x, player.radius, Constants.ARENA_W - player.radius);
        player.y = Mathx.clamp(player.y, player.radius, Constants.ARENA_H - player.radius);

        // Camera smooth follow
        rc.worldCam.position.x = MathUtils.lerp(rc.worldCam.position.x, player.x, Constants.CAM_LERP);
        rc.worldCam.position.y = MathUtils.lerp(rc.worldCam.position.y, player.y, Constants.CAM_LERP);

        // Apply world VP
        rc.applyWorld();

        // Grid (minor 50)
        rc.shapes.begin(ShapeRenderer.ShapeType.Line);
        rc.shapes.setColor(Palette.GRID_MINOR);
        for (int x = 0; x <= Constants.ARENA_W; x += Constants.GRID_MINOR) rc.shapes.line(x, 0, x, Constants.ARENA_H);
        for (int y = 0; y <= Constants.ARENA_H; y += Constants.GRID_MINOR) rc.shapes.line(0, y, Constants.ARENA_W, y);

        // Grid (major 250)
        rc.shapes.setColor(Palette.GRID_MAJOR);
        for (int x = 0; x <= Constants.ARENA_W; x += Constants.GRID_MAJOR) rc.shapes.line(x, 0, x, Constants.ARENA_H);
        for (int y = 0; y <= Constants.ARENA_H; y += Constants.GRID_MAJOR) rc.shapes.line(0, y, Constants.ARENA_W, y);
        rc.shapes.end();

        // Player (cyan)
        rc.shapes.begin(ShapeRenderer.ShapeType.Filled);
        rc.shapes.setColor(Color.valueOf("00D1FFFF"));
        rc.shapes.circle(player.x, player.y, player.radius, 32);
        rc.shapes.end();
    }

    private float accelerateTowards(float current, float target, float dt) {
        float dv = target - current;
        if (Math.abs(dv) < 0.01f) return target;

        float accel = (dv > 0) ? Constants.MOVE_ACCEL : Constants.MOVE_DECEL;
        float step = Math.signum(dv) * accel * dt;

        if (Math.abs(step) > Math.abs(dv)) {
            return target;
        }
        return current + step;
    }

    @Override public void resize(int w, int h) { rc.resize(w, h); }
    @Override public void dispose() { rc.dispose(); }
}