package it.gabryca.playershop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class shops implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (commandSender instanceof Player) {
            if (config.getConfigurationSection("shops") != null) {
                Player p = (Player) commandSender;
                GUI gui = new GUI(p);
                gui.open();
            } else {
                commandSender.sendMessage("§c" + message.getString("Messages.Warn-NoShopsEverCreated"));
            }

        } else {
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-NotAPlayer"));
        }
        return true;
    }
}
