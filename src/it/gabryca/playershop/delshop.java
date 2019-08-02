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
        if (commandSender instanceof Player) {
            if (config.getString("shops." + commandSender.getName() + ".position.name").equals(commandSender.getName())) {
                config.set("shops." + commandSender.getName() + ".position", null);
                config.set("shops." + commandSender.getName(), null);
                config.set("shops." + commandSender.getName() + ".position.name", null);
                config.set("shops." + commandSender.getName() + ".position.X", null);
                config.set("shops." + commandSender.getName() + ".position.Y", null);
                config.set("shops." + commandSender.getName() + ".position.Z", null);
                config.set("shops." + commandSender.getName() + ".position.world", null);
                Main.getInstance().saveConfig();
                commandSender.sendMessage("§a" + config.getString("Messages.Shop-Deleted-Successful"));
            } else {
                commandSender.sendMessage("§c" + config.getString("Messages.Warn-NoShops"));
            }
        } else {
            commandSender.sendMessage("§c" + config.getString("Messages.Warn-NotAPlayer"));
        }

        return true;
    }
}
