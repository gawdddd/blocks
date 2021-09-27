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
import net.minecraft.util.text.TextFormatting;

public class checkblocks extends CommandBase {

    private Config conf = FileHandler.config;

    @Override
    public String getName() {
        return "checkblocks";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return TextFormatting.RED + "Usage: /" + this.getName();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP) {
            if (PermissionUtils.canUse("blocks.command.checkblocks", sender)) {
                if (conf.showBlocks) {
                    this.send(sender, "&5Blocks in config: ");
                    for (String block : conf.blocks) {
                        this.send(sender, "&d" + block);
                    }

                } else {
                    this.send(sender, "&cBlocks not visable unlucko!");
                }
            } else {
                this.send(sender, "&cYou're not allowed to use this command!");
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    private void send(ICommandSender recipient, String message) {
        recipient.sendMessage(new TextComponentString((FileHandler.config.announcerName + message).replaceAll("&([0-9a-fk-or])","\u00a7$1")));
    }
}
