package gg.oddysian.gawdddd.blocks.commands;

import gg.oddysian.gawdddd.blocks.config.Config;
import gg.oddysian.gawdddd.blocks.handlers.FileHandler;
import gg.oddysian.gawdddd.blocks.utils.PermissionUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class blockability extends CommandBase {

    private Config conf = FileHandler.config;

    @Override
    public String getName() {
        return "blockability";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/blockability";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") && PermissionUtils.canUse("blocks.command.blockability.reload", sender)) {
                    FileHandler.readAllFiles(); //doesnt actually read files need fix
                }
                if (args[0].equalsIgnoreCase("show") && PermissionUtils.canUse("blocks.command.blockability.show", sender)) {
                    if (conf.showBlocks) {
                        for (String block : conf.blocks) {
                            this.send(sender, "&d" + block );
                        }
                    } else {
                        this.send(sender, "&cBlocks not visable unlucko!");
                    }
                }
                else {
                    this.send(sender, "&cYou do not have permission to use this command!");
                }
            }
        }
    }

    private void send(ICommandSender recipient, String message) {
        recipient.sendMessage(new TextComponentString((FileHandler.config.announcerName + message).replaceAll("&([0-9a-fk-or])","\u00a7$1")));
    }
}