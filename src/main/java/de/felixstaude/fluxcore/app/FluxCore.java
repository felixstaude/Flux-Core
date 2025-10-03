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
        // ESC → quit
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // Clear
        Gdx.gl.glClearColor(Palette.BACKDROP.r, Palette.BACKDROP.g, Palette.BACKDROP.b, Palette.BACKDROP.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Input (WASD)
        float dt = Gdx.graphics.getDeltaTime();
        float dx = 0f, dy = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy += 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy -= 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx -= 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) dx += 1f;

        if (dx != 0 && dy != 0) {
            float len = (float)Math.sqrt(dx*dx + dy*dy);
            dx /= len; dy /= len;
        }

        // Move + clamp
        player.x += dx * player.speed * dt;
        player.y += dy * player.speed * dt;
        player.x = Mathx.clamp(player.x, player.radius, Constants.ARENA_W - player.radius);
        player.y = Mathx.clamp(player.y, player.radius, Constants.ARENA_H - player.radius);

        // Camera follow
        rc.worldCam.position.x = MathUtils.lerp(rc.worldCam.position.x, player.x, camLerp);
        rc.worldCam.position.y = MathUtils.lerp(rc.worldCam.position.y, player.y, camLerp);

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

    @Override public void resize(int w, int h) { rc.resize(w, h); }
    @Override public void dispose() { rc.dispose(); }
}