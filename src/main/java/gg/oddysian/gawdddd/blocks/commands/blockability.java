package gg.oddysian.gawdddd.blocks.commands;

import gg.oddysian.gawdddd.blocks.config.Config;
import gg.oddysian.gawdddd.blocks.utils.PermissionUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class blockability extends CommandBase {

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
            if (!PermissionUtils.hasPermission("blocks.command.blockability", (EntityPlayerMP) sender)) {
                for (String block : Config.blocks) {
                    sender.sendMessage(new TextComponentString(TextFormatting.AQUA + block.toString() + ", "));
                }

            } else {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + "You do not have permission to use this command!"));
            }
        }
    }
}
