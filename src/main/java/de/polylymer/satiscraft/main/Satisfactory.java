package de.polylymer.satiscraft.main;

import de.polylymer.satiscraft.commands.implementation.EventCommand;
import de.polylymer.satiscraft.config.Localization;
import de.polylymer.satiscraft.internal.FactoryGameCrashReport;
import de.polylymer.satiscraft.io.ISaveGame;
import de.polylymer.satiscraft.io.ISaveGameBuilder;
import mooziii.annotation.PluginApp;
import mooziii.config.Config;
import mooziii.plugin.BSpigotApplication;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

@PluginApp(main = Satisfactory.class)
public class Satisfactory extends BSpigotApplication {

    private static Satisfactory satisfactory;
    private static ISaveGame saveGame;

    public ISaveGame getSaveGame() {
        return saveGame;
    }

    @Override
    public String name() {
        return "Satiscraft";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public JavaPlugin core() {
        return this;
    }

    public static Satisfactory getFactory() {
        return satisfactory;
    }

    @Override
    public void load() {
        Config.API_UPDATE_INTERVAL = 60;
        Config.WEBAPI = false; // Web-API disabled by default so mods have to turn them on!
        new Localization();
    }

    @Override
    public void startup() {
        satisfactory = this;
        saveGame = new ISaveGameBuilder().setLastModification(new Date()).build();
        new EventCommand();
    }

    @Override
    public void shutdown() {
        //write heap to temp.yml and read temp.yml and put stuff back on heap
        satisfactory = null;
    }
}
