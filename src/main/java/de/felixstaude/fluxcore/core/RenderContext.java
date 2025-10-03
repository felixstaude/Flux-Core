package de.felixstaude.fluxcore.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.felixstaude.fluxcore.world.Constants;

public class RenderContext {
    public final OrthographicCamera worldCam;
    public final ScreenViewport worldVp;
    public final ShapeRenderer shapes;
    public final SpriteBatch batch;

    public RenderContext() {
        worldCam = new OrthographicCamera();
        worldVp = new ScreenViewport(worldCam);
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
        int sx = m;
        int sy = m;

        worldVp.setScreenBounds(sx, sy, vw, vh);
        worldVp.update(vw, vh, false);

        worldCam.viewportWidth = vw;
        worldCam.viewportHeight = vh;
        worldCam.update();
    }

    public void dispose() {
        shapes.dispose();
        batch.dispose();
    }
}
