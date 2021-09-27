package gg.oddysian.gawdddd.blocks.commands;

import gg.oddysian.gawdddd.blocks.config.Config;
import gg.oddysian.gawdddd.blocks.handlers.FileHandler;
import gg.oddysian.gawdddd.blocks.utils.PermissionManager;
import gg.oddysian.gawdddd.blocks.utils.PermissionUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class command extends CommandBase {

    private Config conf = FileHandler.config;

    @Override
    public String getName() {
        return "blockability";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return TextFormatting.RED + "Usage: /" + this.getName();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP) {

            if (args.length == 0)
                getUsage(sender);

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") && PermissionUtils.hasPermission(PermissionManager.ADMIN_RELOAD, (EntityPlayerMP) sender)) {
                    FileHandler.readAllFiles();
                    this.send(sender, "&aReloaded config! Check console for errors");

                } else {
                    this.send(sender, "&cYou're not allowed to use this command!");
                }
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

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> possibleArgs = new ArrayList<>();
        if (args.length == 1) {
            possibleArgs.add("reload");
        }
        return getListOfStringsMatchingLastWord(args, possibleArgs);
    }
}