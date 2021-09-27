package gg.oddysian.gawdddd.blocks;

import gg.oddysian.gawdddd.blocks.commands.checkblocks;
import gg.oddysian.gawdddd.blocks.commands.command;
import gg.oddysian.gawdddd.blocks.handlers.FileHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

@Mod(
        modid = Blocks.MOD_ID,
        name = Blocks.MOD_NAME,
        version = Blocks.VERSION,
        serverSideOnly = true,
        acceptableRemoteVersions = "*"
)
public class Blocks {

    public static final String MOD_ID = "blocks";
    public static final String MOD_NAME = "Blocks";
    public static final String VERSION = "1.0";
    public static final String AUTHOR = "Gawdddd";

    public static Logger log = LogManager.getLogger(MOD_NAME);
    public static File config;
    public static File configDir;

    @Mod.Instance(MOD_ID)
    public static Blocks INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) throws IOException {
        log.info("---------------------------------------");
        log.info("Mod: " + MOD_NAME);
        log.info("Version: " + VERSION);
        log.info("By: " + AUTHOR);
        log.info("---------------------------------------");

        configDir = new File(event.getModConfigurationDirectory() + "/Blocks/"); // Creates directory Blocks
        configDir.mkdir();
        config = new File(configDir, "config.conf"); // Creates config file
        config.createNewFile();

        FileHandler.readAllFiles(); // Reads config files
        FileHandler.creationCheck(); // Runs creation check for config files
        FileHandler.writeAllFiles(); // Writes to config files
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new command());
        event.registerServerCommand(new checkblocks());
    }
}