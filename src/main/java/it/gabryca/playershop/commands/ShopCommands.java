package it.gabryca.playershop.commands;

import it.gabryca.playershop.PlayerShop;
import it.gabryca.playershop.playershop_api.ShopUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ShopCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        Player p = null;

        if (sender instanceof Player){
            p = (Player) sender;
        }

        if (args.length == 0) {

            sender.sendMessage(PlayerShop.format("&7&bCommands:"));
            sender.sendMessage(PlayerShop.format("&3&b - &3 /pshops -> Opens GUI"));
            sender.sendMessage(PlayerShop.format("&3&b - &3 /pshops myshops -> Open Private GUI"));
            sender.sendMessage(PlayerShop.format("&3&b - &3 /pshop create <optional - shoplogo>"));
            sender.sendMessage(PlayerShop.format("&3&b - &3 /pshop shoplogo <Block_ID - shoplogo> <Shop-ID>"));
            sender.sendMessage(PlayerShop.format("&3&b - &3 /pshop delete <Optional - ShopNumber>"));
            sender.sendMessage(PlayerShop.format("&3&b - &3 /pshop tp <Owner> <Optional - ShopNumber>"));
            return true;

        } else if (args[0].equalsIgnoreCase("create")){

            if (p == null){
                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Sender_Is_Console")));
                return true;
            }

            if (args.length == 2){
                ShopUtil.get().createShop(Optional.ofNullable(args[1]), p);
            } else {
                ShopUtil.get().createShop(Optional.empty(), p);
            }

        } else if (args[0].equalsIgnoreCase("shoplogo")){

            // Command to set a new shopLogo

            if (args.length == 1){
                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Logo_Needed")));
                return true;
            }

            if (args.length == 2){
                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_ID_Needed")));
                return true;
            }

            int ID = 0;

            try {
                ID = Integer.parseInt(args[2]);
            } catch (NumberFormatException e){
                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_ID_Not_An_Integer")));
            }

            if (ID != 0) {
                ShopUtil.get().shopLogoEdit(ID, sender, args[1]);
            }

        } else if (args[0].equalsIgnoreCase("delete")){

            int ID = 0;

            if (args.length == 2){
             try {
                 ID = Integer.parseInt(args[1]);
             } catch (NumberFormatException ignored){}
            }

            // Command to delete a shop
            ShopUtil.get().deletePlayerShop(sender, Optional.of(ID));

        } else if (args[0].equalsIgnoreCase("tp")){

            if (p == null) {
                sender.sendMessage(PlayerShop.format(messages.getString("Messages.Sender_Is_Console")));
                return true;
            }

            if (args.length == 1){
                p.sendMessage(PlayerShop.format(messages.getString("Messages.Specify_Shop_Owner")));
                return true;
            }

            int ID = 0;

            if (args.length == 3) {
                try {
                    ID = Integer.parseInt(args[2]);
                } catch (NumberFormatException ignored) {
                }
            }

            // Command to TP to a shop
            ShopUtil.get().shopTP(args[1], Optional.of(ID),  p);

        } else {
            sender.sendMessage(PlayerShop.format(messages.getString("Messages.Unknown_Pshop_SubCommand")));
        }

        return true;
    }
}
