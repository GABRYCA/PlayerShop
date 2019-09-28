package it.gabryca.playershop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class Playershop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (commandSender.hasPermission(config.getString("Permissions.playershop-command"))){
            commandSender.sendMessage("§7§lCommands:");
            commandSender.sendMessage("§7 - §b /shops");
            commandSender.sendMessage("§7 - §b /Playershop");
            commandSender.sendMessage("§7 - §b /Shoplogo <Block-ID> <Shop_Number>");
        } else {
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-permission") + " " +  config.getString("Permissions.playershop-command"));
        }
        return true;
    }

}
