package de.felixstaude.fluxcore.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.felixstaude.fluxcore.core.RenderContext;
import de.felixstaude.fluxcore.util.Palette;
import de.felixstaude.fluxcore.world.Constants;

public class FluxCore extends ApplicationAdapter {
    private RenderContext rc;

    @Override
    public void create() {
        rc = new RenderContext();
        rc.worldCam.position.set(Constants.ARENA_W / 2f, Constants.ARENA_H / 2f, 0);
        rc.worldCam.update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(Palette.BACKDROP.r, Palette.BACKDROP.g, Palette.BACKDROP.b, Palette.BACKDROP.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rc.applyWorld();

        // Draw grid
        rc.shapes.begin(ShapeRenderer.ShapeType.Line);

        // Minor grid lines (every 50)
        rc.shapes.setColor(Palette.GRID_MINOR);
        for (int x = 0; x <= Constants.ARENA_W; x += Constants.GRID_MINOR) {
            rc.shapes.line(x, 0, x, Constants.ARENA_H);
        }
        for (int y = 0; y <= Constants.ARENA_H; y += Constants.GRID_MINOR) {
            rc.shapes.line(0, y, Constants.ARENA_W, y);
        }

        // Major grid lines (every 250)
        rc.shapes.setColor(Palette.GRID_MAJOR);
        for (int x = 0; x <= Constants.ARENA_W; x += Constants.GRID_MAJOR) {
            rc.shapes.line(x, 0, x, Constants.ARENA_H);
        }
        for (int y = 0; y <= Constants.ARENA_H; y += Constants.GRID_MAJOR) {
            rc.shapes.line(0, y, Constants.ARENA_W, y);
        }

        rc.shapes.end();

        // Draw test shapes
        rc.shapes.begin(ShapeRenderer.ShapeType.Filled);
        rc.shapes.setColor(1f, 0.5f, 0f, 1f); // Orange
        rc.shapes.circle(Constants.ARENA_W / 2f - 100, Constants.ARENA_H / 2f, 50);
        rc.shapes.setColor(0f, 1f, 0.5f, 1f); // Cyan
        rc.shapes.rect(Constants.ARENA_W / 2f + 50, Constants.ARENA_H / 2f - 50, 100, 100);
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
