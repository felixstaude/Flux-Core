package de.felixstaude.fluxcore.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("FLUX CORE");
        cfg.useVsync(true);
        cfg.setBackBufferConfig(8, 8, 8, 8, 24, 8, 4); // MSAA x4

        var mode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        if (mode != null) {
            cfg.setFullscreenMode(mode);
        } else {
            cfg.setWindowedMode(1280, 800);
        }
        new Lwjgl3Application(new FluxCore(), cfg);
    }
}