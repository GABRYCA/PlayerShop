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
                Player p = (Player) commandSender;
                MainGUI gui = new MainGUI(p);
                gui.open();

        } else {
            commandSender.sendMessage("§c" + message.getString("Messages.Warn-NotAPlayer"));
        }
        return true;
    }
}
