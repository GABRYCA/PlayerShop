package it.gabryca.playershop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class shopvisit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Configuration config = Main.getInstance().getConfig();
        if (commandSender instanceof Player){
            if (strings.length == 1){
                if (config.getString("shops." + strings[0] + ".position.name") == null){
                    commandSender.sendMessage("§c" + config.getString("Messages.Warn-NoShops"));
                    return true;
                }
                if (config.getString("shops." + strings[0] + ".position.name").equals(strings[0])){
                    double x = config.getDouble("shops."+ strings[0] + ".position.X");
                    double y = config.getDouble("shops." + strings[0] + ".position.Y");
                    double z = config.getDouble("shops." +  strings[0] + ".position.Z");
                    String worldname = config.getString("shops." + strings[0] + ".position.world");
                    World world = Main.getInstance().getServer().getWorld(worldname);
                    ((Player) commandSender).teleport(new Location(world,x,y,z));
                    commandSender.sendMessage("§a" + config.getString("Messages.Shop-Teleport-Successful"));
                }
            } else {
                commandSender.sendMessage("§c" + config.getString("Messages.Warn-WrongFormat"));
            }
        } else {
            commandSender.sendMessage("§c" + config.getString("Messages.Warn-NotAPlayer"));
        }
        return true;
    }
}
