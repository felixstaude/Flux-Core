package de.felixstaude.fluxcore.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.felixstaude.fluxcore.world.Constants;

public class RenderContext {
    public final OrthographicCamera worldCam;
    public final FitViewport worldVp;
    public final ShapeRenderer shapes;
    public final SpriteBatch batch;

    public boolean debugViewportBounds = false;

    public RenderContext() {
        worldCam = new OrthographicCamera();
        worldVp = new FitViewport(Constants.ARENA_W, Constants.ARENA_H, worldCam);
        shapes = new ShapeRenderer();
        batch = new SpriteBatch();
    }

    public void applyWorld() {
        worldVp.apply(true);
        worldCam.update();
        shapes.setProjectionMatrix(worldCam.combined);
        batch.setProjectionMatrix(worldCam.combined);
        Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl.GL_SRC_ALPHA, Gdx.gl.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void resize(int width, int height) {
        int m = Constants.SCREEN_MARGIN;
        int vw = Math.max(1, width - 2 * m);
        int vh = Math.max(1, height - 2 * m);

        worldVp.setScreenBounds(m, m, vw, vh);
        worldVp.update(vw, vh, false);
        worldCam.update();
    }

    public void drawDebugViewportBounds() {
        if (!debugViewportBounds) return;

        // Draw in screen space (identity matrix)
        shapes.setProjectionMatrix(new com.badlogic.gdx.math.Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        int m = Constants.SCREEN_MARGIN;
        int vw = Math.max(1, Gdx.graphics.getWidth() - 2 * m);
        int vh = Math.max(1, Gdx.graphics.getHeight() - 2 * m);

        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setColor(Color.CYAN);
        shapes.rect(m, m, vw, vh);
        shapes.end();
    }

    public void dispose() {
        shapes.dispose();
        batch.dispose();
    }
}
