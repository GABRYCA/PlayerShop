package it.gabryca.playershop;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesYML {

    private File file = new File(Main.getInstance().getDataFolder()+"/messages.yml");
    private FileConfiguration conf;

    public MessagesYML() {
        if(!file.exists()){
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                conf.createSection("Messages");
                conf.set("Messages.Warn-PlayerAlreadyHaveShop", "You already created a shop, please delete it if you want to make a new one");
                conf.set("Messages.Warn-NotAPlayer", "You aren't a player (Hi console!)");
                conf.set("Messages.Warn-permission", "You don't have the permission to do that");
                conf.set("Messages.Warn-WrongFormat", "Wrong format, try to add the player name or block");
                conf.set("Messages.Warn-NoShops", "There aren't shops of that player");
                conf.set("Messages.Warn-NoShopsEverCreated", "There isn't a -shops:- section in the config, this could mean no shops got ever created! Create one now with /setshop");
                conf.set("Messages.Warn-NotOnGround", "You must be on the ground to set a shop spawnpoint! -_-");
                conf.set("Messages.Shop-Set-Successful", "Shop created with success on your location!");
                conf.set("Messages.Shop-Teleport-Successful", "Teleported to the shop with success");
                conf.set("Messages.Shop-Deleted-Successful", "Shop deleted with success!");
                conf.set("Messages.Click-to-teleport", "Click to teleport to this playershop");
                conf.set("Messages.Warn-DontHaveShop", "You don't have a shop");
                conf.set("Messages.Warn-NotABlock", "That's not a valid block ID");
                conf.set("Messages.Shop-Logo-Set", "Shop logo changed with success");
                conf.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getFile(){
        return conf;
    }

}
