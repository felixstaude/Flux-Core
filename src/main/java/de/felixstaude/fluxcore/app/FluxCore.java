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
import de.felixstaude.fluxcore.util.Mathx;
import de.felixstaude.fluxcore.util.Palette;
import de.felixstaude.fluxcore.world.Constants;

public class FluxCore extends ApplicationAdapter {
    private RenderContext rc;
    private Player player;
    private float camLerp = 0.1f;

    @Override
    public void create() {
        rc = new RenderContext();
        player = new Player(Constants.ARENA_W / 2f, Constants.ARENA_H / 2f);
        rc.worldCam.position.set(player.x, player.y, 0);
        rc.worldCam.update();
    }

    @Override
    public void render() {
        // Handle ESC to quit
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // Clear
        Gdx.gl.glClearColor(Palette.BACKDROP.r, Palette.BACKDROP.g, Palette.BACKDROP.b, Palette.BACKDROP.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Input - WASD movement
        float delta = Gdx.graphics.getDeltaTime();
        float dx = 0, dy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) dx += 1;

        // Normalize diagonal movement
        if (dx != 0 && dy != 0) {
            float len = (float) Math.sqrt(dx * dx + dy * dy);
            dx /= len;
            dy /= len;
        }

        // Apply movement
        player.x += dx * player.speed * delta;
        player.y += dy * player.speed * delta;

        // Clamp player to arena bounds
        player.x = Mathx.clamp(player.x, player.radius, Constants.ARENA_W - player.radius);
        player.y = Mathx.clamp(player.y, player.radius, Constants.ARENA_H - player.radius);

        // Camera smooth follow
        rc.worldCam.position.x = MathUtils.lerp(rc.worldCam.position.x, player.x, camLerp);
        rc.worldCam.position.y = MathUtils.lerp(rc.worldCam.position.y, player.y, camLerp);

        // Apply world viewport
        rc.applyWorld();

        // Draw grid (minor lines every 50)
        rc.shapes.begin(ShapeRenderer.ShapeType.Line);
        rc.shapes.setColor(Palette.GRID_MINOR);
        for (int x = 0; x <= Constants.ARENA_W; x += Constants.GRID_MINOR) {
            rc.shapes.line(x, 0, x, Constants.ARENA_H);
        }
        for (int y = 0; y <= Constants.ARENA_H; y += Constants.GRID_MINOR) {
            rc.shapes.line(0, y, Constants.ARENA_W, y);
        }

        // Draw grid (major lines every 250)
        rc.shapes.setColor(Palette.GRID_MAJOR);
        for (int x = 0; x <= Constants.ARENA_W; x += Constants.GRID_MAJOR) {
            rc.shapes.line(x, 0, x, Constants.ARENA_H);
        }
        for (int y = 0; y <= Constants.ARENA_H; y += Constants.GRID_MAJOR) {
            rc.shapes.line(0, y, Constants.ARENA_W, y);
        }
        rc.shapes.end();

        // Draw player (cyan circle)
        rc.shapes.begin(ShapeRenderer.ShapeType.Filled);
        rc.shapes.setColor(Color.valueOf("00D1FFFF"));
        rc.shapes.circle(player.x, player.y, player.radius, 32);
        rc.shapes.end();
    }

    @Override
    public void resize(int width, int height) {
        rc.resize(width, height);
    }

    @Override
    public void dispose() {
        rc.dispose();
    }
}
