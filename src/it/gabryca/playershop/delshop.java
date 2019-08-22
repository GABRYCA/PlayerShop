package it.gabryca.playershop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class delshop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();
        if (commandSender instanceof Player) {
            if (strings.length == 1){
                if (commandSender.hasPermission(config.getString("Permissions.delshop-others"))){
                    if (config.getString("shops." + strings[0] + ".position.name") != null){
                        config.set("shops." + strings[0] + ".position", null);
                        config.set("shops." + strings[0], null);
                        config.set("shops." + strings[0] + ".position.name", null);
                        config.set("shops." + strings[0] + ".position.X", null);
                        config.set("shops." + strings[0] + ".position.Y", null);
                        config.set("shops." + strings[0] + ".position.Z", null);
                        config.set("shops." + strings[0] + ".position.world", null);
                        Main.getInstance().saveConfig();
                        commandSender.sendMessage("§a" + message.getString("Messages.Shop-Deleted-Successful") + " [ " + strings[0] + " ]");
                    } else {
                        commandSender.sendMessage("§c" + message.getString("Messages.Warn-NoShops"));
                    }
                } else {
                    commandSender.sendMessage("§c" + message.getString("Messages.Warn-permission") + " " + config.getString("Permissions.delshop-others"));
                }
            } else {
                if (config.getString("shops." + commandSender.getName() + ".position.name") != null) {
                    config.set("shops." + commandSender.getName() + ".position", null);
                    config.set("shops." + commandSender.getName(), null);
                    config.set("shops." + commandSender.getName() + ".position.name", null);
                    config.set("shops." + commandSender.getName() + ".position.X", null);
                    config.set("shops." + commandSender.getName() + ".position.Y", null);
                    config.set("shops." + commandSender.getName() + ".position.Z", null);
                    config.set("shops." + commandSender.getName() + ".position.world", null);
                    Main.getInstance().saveConfig();
                    commandSender.sendMessage("§a" + message.getString("Messages.Shop-Deleted-Successful"));
                } else {
                    commandSender.sendMessage("§c" + message.getString("Messages.Warn-NoShops"));
                }
            }
        } else {
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-NotAPlayer"));
        }

        return true;
    }
}
