package de.felixstaude.fluxcore.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.felixstaude.fluxcore.world.Constants;

public class HudStage {
    private final Stage stage;
    private final ShapeRenderer shapeRenderer;

    private Label energyLabel;

    private int hp = 100;
    private int maxHp = 100;
    private int energy = 0;

    private float hpBarX, hpBarY, hpBarWidth, hpBarHeight;

    public HudStage() {
        stage = new Stage(new ScreenViewport());
        shapeRenderer = new ShapeRenderer();

        Label.LabelStyle style = new Label.LabelStyle(UiAssets.defaultFont, Color.WHITE);

        energyLabel = new Label("Energy: 0", style);

        stage.addActor(energyLabel);

        hpBarWidth = 180f;
        hpBarHeight = 14f;
    }

    public void update(int hp, int maxHp, int energy) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.energy = energy;

        energyLabel.setText(String.format("Energy: %d", energy));
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        int m = Constants.SCREEN_MARGIN;
        int yTop = Gdx.graphics.getHeight() - m;

        // HP bar top-left (inside margin)
        hpBarX = m + 10;
        hpBarY = yTop - 20 - hpBarHeight;

        // Energy label below HP bar
        energyLabel.setPosition(hpBarX, hpBarY - 25, Align.left);
    }

    public void act(float delta) {
        stage.act(delta);
    }

    public void draw() {
        stage.draw();

        // Draw HP bar with ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Background
        shapeRenderer.setColor(UiAssets.BAR_BG);
        shapeRenderer.rect(hpBarX, hpBarY, hpBarWidth, hpBarHeight);

        // HP fill
        float fillWidth = hpBarWidth * ((float) hp / maxHp);
        shapeRenderer.setColor(UiAssets.getHpColor(hp, maxHp));
        shapeRenderer.rect(hpBarX, hpBarY, fillWidth, hpBarHeight);

        shapeRenderer.end();

        // Border
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(UiAssets.BAR_BORDER);
        shapeRenderer.rect(hpBarX, hpBarY, hpBarWidth, hpBarHeight);
        shapeRenderer.end();
    }

    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();
    }
}
