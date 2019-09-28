package it.gabryca.playershop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class shoplogo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (!(commandSender.hasPermission(config.getString("Permissions.setshop")))){
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-permission") + " " +  config.getString("Permissions.setshop"));
            return true;
        }

        if (!(strings.length == 2)){
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-WrongFormat"));
            return true;
        }

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-NotAPlayer"));
            return true;
        }

        if (Material.getMaterial(strings[0]) == null){
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-NotABlock"));
            return true;
        }

        if ((config.getString("shops." + commandSender.getName() + "." + strings[1] + ".position.name" ) == null)){
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-DontHaveShop"));
            return true;
        }

        config.set("shops." + commandSender.getName() + "." + strings[1] + ".position.block", strings[0]);
        Main.getInstance().saveConfig();
        commandSender.sendMessage("§a" + message.getString("Messages.Shop-Logo-Set"));
        return true;
    }
}
