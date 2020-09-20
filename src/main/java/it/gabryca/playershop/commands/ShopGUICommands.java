package it.gabryca.playershop.commands;

import it.gabryca.playershop.PlayerShop;
import it.gabryca.playershop.gui.PGUI;
import it.gabryca.playershop.gui.PGUIPersonal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class ShopGUICommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Configuration messages = PlayerShop.getMessages();
        Configuration config = PlayerShop.getInstance().getConfig();

        try {
            String test = args[0];
        } catch (ArrayIndexOutOfBoundsException e){

            if (!(sender instanceof Player)){

                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Sender_Is_Console")));

            } else {

                Player p = (Player) sender;

                PGUI gui = new PGUI(p);
                gui.open();

            }

            return true;
        }

        if (args[0].equalsIgnoreCase("myshops")){

            if (!(sender instanceof Player)){

                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Sender_Is_Console")));

            } else {

                Player p = (Player) sender;

                PGUIPersonal gui = new PGUIPersonal(p);
                gui.open();

            }

        } else {
            sender.sendMessage(PlayerShop.format(messages.getString("Messages.Unknown_Pshops_SubCommand")));
        }

        return true;
    }
}
