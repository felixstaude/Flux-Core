package de.felixstaude.fluxcore.core;

import com.badlogic.gdx.Gdx;
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
        worldVp.update(width, height, true);
    }

    public void dispose() {
        shapes.dispose();
        batch.dispose();
    }
}
