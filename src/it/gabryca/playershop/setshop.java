package it.gabryca.playershop;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class setshop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Configuration config = Main.getInstance().getConfig();
        if (commandSender instanceof Player){
            if (commandSender.hasPermission(config.getString("Permissions.setshop"))){
                if (!(((Player) commandSender).isFlying())){
                    Location loc = ((Player) commandSender).getLocation();
                    String world = loc.getWorld().getName();
                    double X = loc.getX();
                    double Y = loc.getY();
                    double Z = loc.getZ();
                    config.set("shops." + commandSender.getName() + ".position.name", commandSender.getName());
                    config.set("shops." + commandSender.getName() + ".position.X", X);
                    config.set("shops." + commandSender.getName() + ".position.Y", Y);
                    config.set("shops." + commandSender.getName() + ".position.Z", Z);
                    config.set("shops." + commandSender.getName() + ".position.world", world);
                    Main.getInstance().saveConfig();
                    commandSender.sendMessage("§a" + config.getString("Messages.Shop-Set-Successful"));
                } else {
                    commandSender.sendMessage("§c" + config.getString("Messages.Warn-NotOnGround"));
                }
            } else {
                commandSender.sendMessage("§c" + config.getString("Messages.Warn-permission") + " " +  Main.getInstance().getConfig().getString("Permissions.setshop"));
            }
        } else {
            commandSender.sendMessage("§c" + config.getString("Messages.Warn-NotAPlayer"));
        }
        return true;
    }
}
