package gg.oddysian.gawdddd.blocks.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.oddysian.gawdddd.blocks.Blocks;
import gg.oddysian.gawdddd.blocks.config.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandler {
    public static Config config = new Config();

    public static void creationCheck() {
        if (config == null)
            config = new Config();
    }

    public static void loadConfigFile(File file) {
        try {
            if (!file.exists())
                file.createNewFile();
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(file));
            config = gson.fromJson(br, Config.class);
            br.close();
        } catch (Exception e) {
            Blocks.log.error("Failed to read Blocks config file:\r\n" + e.getMessage());
        }
    }

    public static void writeConfigFile(File file) {
        try {
            if (!file.exists())
                file.createNewFile();
            Gson gson = (new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create());
            String json = gson.toJson(config);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            Blocks.log.error("Failed to save Blocks config file:\r\n" + e.getMessage());
        }
    }

    public static void readAllFiles() {
        readConfig();
    }

    public static void readConfig() {
        loadConfigFile(Blocks.config);
    }

    public static void writeAllFiles() {
        writeConfig();
    }

    public static void writeConfig() {
        writeConfigFile(Blocks.config);
    }
}
