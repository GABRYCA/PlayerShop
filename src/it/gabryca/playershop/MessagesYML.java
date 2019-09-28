package it.gabryca.playershop;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

class MessagesYML {

    private File file = new File(Main.getInstance().getDataFolder()+"/messages.yml");
    private FileConfiguration conf;

    MessagesYML() {
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
                conf.set("Messages.Warn-NoShopsEverCreated", "There isn't a -shops:- section in the config, this could mean no shops got ever created! Create one now with /shops");
                conf.set("Messages.Warn-NotOnGround", "You must be on the ground to set a shop spawnpoint! -_-");
                conf.set("Messages.Shop-Set-Successful", "Shop created with success on your location!");
                conf.set("Messages.Shop-Teleport-Successful", "Teleported to the shop with success");
                conf.set("Messages.Shop-Deleted-Successful", "Shop deleted with success!");
                conf.set("Messages.Click-to-teleport", "Click to teleport to this playershop");
                conf.set("Messages.Warn-DontHaveShop", "You don't have a shop");
                conf.set("Messages.Warn-NotABlock", "That's not a valid block ID");
                conf.set("Messages.Shop-Logo-Set", "Shop logo changed with success");
                conf.set("Messages.Shop-Your", "Your Shops");
                conf.set("Messages.Shop-Click-Open", "Click to open");
                conf.set("Messages.Shop-Public", "Public shops");
                conf.set("Messages.Shop-Add-Block", "Add Block");
                conf.set("Messages.Shop-RightClick-Delete", "Right click to delete §lALL!");
                conf.set("Messages.Shop-LeftClick-Logo", "Left click to change logo");
                conf.set("Messages.Shop-None", "§1§l/shoplogo");
                conf.set("Messages.Shop-GoTo", "§1/shoplogo <BLOCK_ID> <Shop_Number>");
                conf.set("Messages.Shop-Set-Here", "Click me to add a shop on your location");
                conf.set("Messages.Shop-Add", "Add a Shop");
                conf.set("Messages.Shop-Limit", "Shop limit:");
                conf.set("Messages.Shop-Limit-Reached", "You reached the shops limit");
                conf.set("Messages.Shop-First-Set-Successful", "First Shop created with success on your location!");
                conf.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    FileConfiguration getFile(){
        return conf;
    }

}
