package de.felixstaude.fluxcore.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class UiAssets {
    public static BitmapFont defaultFont;
    public static BitmapFont labelFont;

    public static final Color HP_GREEN = Color.valueOf("00FF88FF");
    public static final Color HP_YELLOW = Color.valueOf("FFDD00FF");
    public static final Color HP_RED = Color.valueOf("FF3333FF");
    public static final Color TEXT_WHITE = Color.WHITE;
    public static final Color TEXT_DARK = Color.valueOf("222222FF");
    public static final Color BAR_BG = Color.valueOf("1A1A1AFF");
    public static final Color BAR_BORDER = Color.valueOf("444444FF");

    public static void load() {
        // Use LibGDX default font
        defaultFont = new BitmapFont();
        defaultFont.getData().setScale(1.2f);
        defaultFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        labelFont = new BitmapFont();
        labelFont.getData().setScale(1.0f);
        labelFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public static void dispose() {
        if (defaultFont != null) defaultFont.dispose();
        if (labelFont != null) labelFont.dispose();
    }

    public static Color getHpColor(int hp, int maxHp) {
        float ratio = (float) hp / maxHp;
        if (ratio > 0.6f) return HP_GREEN;
        if (ratio > 0.3f) return HP_YELLOW;
        return HP_RED;
    }
}
