package de.felixstaude.fluxcore;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class FluxCore extends ApplicationAdapter {
    @Override
    public void render() {
        Gdx.gl.glClearColor(0.043f, 0.071f, 0.125f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}