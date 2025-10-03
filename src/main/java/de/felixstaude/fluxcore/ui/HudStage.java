package de.felixstaude.fluxcore.ui;

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

    private Label waveTimerLabel;
    private Label killsLabel;
    private Label energyLabel;

    private int hp = 100;
    private int maxHp = 100;
    private int energy = 0;

    private float hpBarX, hpBarY, hpBarWidth, hpBarHeight;

    public HudStage() {
        stage = new Stage(new ScreenViewport());
        shapeRenderer = new ShapeRenderer();

        Label.LabelStyle style = new Label.LabelStyle(UiAssets.defaultFont, Color.WHITE);

        waveTimerLabel = new Label("WAVE 1  •  00:00", style);
        killsLabel = new Label("Kills: 0", style);
        energyLabel = new Label("Energy: 0", style);

        stage.addActor(waveTimerLabel);
        stage.addActor(killsLabel);
        stage.addActor(energyLabel);

        hpBarWidth = 200f;
        hpBarHeight = 20f;
    }

    public void setValues(int wave, float timeLeftSec, int hp, int maxHp, int energy, int kills) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.energy = energy;

        int min = (int) (timeLeftSec / 60);
        int sec = (int) (timeLeftSec % 60);
        waveTimerLabel.setText(String.format("WAVE %d  •  %02d:%02d", wave, min, sec));
        killsLabel.setText(String.format("Kills: %d", kills));
        energyLabel.setText(String.format("Energy: %d", energy));
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        int mx = Constants.SCREEN_MARGIN;

        // HP bar top-left (inside margin)
        hpBarX = mx + 16;
        hpBarY = height - mx - 16 - hpBarHeight;

        // Energy label below HP bar
        energyLabel.setPosition(hpBarX, hpBarY - 30, Align.left);

        // Wave/Timer top-center
        waveTimerLabel.setPosition(width / 2f, height - mx - 16, Align.top);

        // Kills top-right
        killsLabel.setPosition(width - mx - 16, height - mx - 16, Align.topRight);
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
