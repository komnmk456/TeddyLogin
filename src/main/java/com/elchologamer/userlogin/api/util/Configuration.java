package com.elchologamer.userlogin.api.util;

import com.elchologamer.userlogin.UserLogin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public abstract class Configuration {

    protected @Nullable String name;
    protected @Nullable File file;
    protected FileConfiguration fileConfiguration;

    /**
     * Represents a YAML configuration file.
     *
     * @param name The name/path of the file. (E.g. "messages.yml")
     */
    public Configuration(@Nullable String name) {
        this.name = name;
    }

    /**
     * Creates the file, adds its default values and loads the configuration.
     */
    public void setup() {
        if (name == null) return;
        file = new File(UserLogin.getPlugin().getDataFolder(), name);

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create \"" + name + ".yml\" file!");
            e.printStackTrace();
        }

        reload();
    }

    /**
     * Registers the default values for the file.
     */
    public abstract void registerDefaults();

    /**
     * Gets the file configuration for this file.
     *
     * @return YAML file configuration.
     */
    public FileConfiguration get() {
        return fileConfiguration;
    }

    /**
     * Reloads the YAML configuration of this file.
     */
    public void reload() {
        if (file == null) return;
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        registerDefaults();
    }

    /**
     * Saves the YAML file configuration to the file.
     */
    public void save() {
        if (file == null) return;

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            System.out.println("Error while trying to save \"" + name + ".yml\"!");
            e.printStackTrace();
        }
    }
}
