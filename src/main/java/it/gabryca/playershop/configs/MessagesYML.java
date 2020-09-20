package it.gabryca.playershop.configs;

import it.gabryca.playershop.PlayerShop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesYML {

    private FileConfiguration conf;

    public MessagesYML(){

        // Filepath
        File file = new File(PlayerShop.getInstance().getDataFolder() + "/Messages.yml");

        // Everything's here
        values();

        // Get the final config
        conf = YamlConfiguration.loadConfiguration(file);
    }

    private void dataConfig(String path, String string){

        // Filepath
        File file = new File(PlayerShop.getInstance().getDataFolder() + "/Messages.yml");

        // Check if the config exists
        if(!file.exists()){
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                conf.set(path, PlayerShop.format(string));
                conf.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean newValue = false;
                conf = YamlConfiguration.loadConfiguration(file);
                if (getFile().getString(path) == null){
                    conf.set(path, PlayerShop.format(string));
                    newValue = true;
                }
                if (newValue) {
                    conf.save(file);
                }
            } catch (IOException e2){
                e2.printStackTrace();
            }
        }

        // Get the final config
        conf = YamlConfiguration.loadConfiguration(file);

    }

    private void values(){
        dataConfig("Messages.Exceed_Or_Dont_Have_Shops", "&cThe player Exceeded the maximum number of shops or don't have one.");
        dataConfig("Messages.Shop_Set_Successful", "&aThe Shop got added with success.");
        dataConfig("Messages.Shop_Logo_Not_A_Block", "&cThe Shop Logo Block doesn't exists, using default.");
        dataConfig("Messages.Shop_Logo_Default_Not_A_Block", "&cThe Shop Logo Block by default in the Config.yml doesn't exists.");
        dataConfig("Messages.Shop_Logo_Edited_With_Success", "&aThe Shop Logo got edited with success!");
        dataConfig("Messages.Shop_Number_Not_Found", "&cCan't find a shop with that number, check your shops with /pshops.");
        dataConfig("Messages.Shop_Deleted_With_Success", "&aShop deleted with success!");
        dataConfig("Messages.Shop_ID_Not_An_Integer", "&cThe Shop Number isn't an integer, the command should looks like this: /pshop shoplogo COAL_ORE 1");
        dataConfig("Messages.Shop_ID_Needed", "&cYou must specify a shop number, check them with /pshops.");
        dataConfig("Messages.Shop_Logo_Needed", "&cYou must specify a shop Block as a the Logo.");
        dataConfig("Messages.Sender_Is_Console", "&cYou can't run this command from the console, sorry!");
        dataConfig("Messages.Shop-Teleport-Successful", "&aTeleported to the shop with success!");
        dataConfig("Messages.Shop_Trap_Teleported_Back", "&cYou've been teleported on a -flying- location which might have been a trap, teleporting to a safe zone...");
        dataConfig("Messages.GUI_No_Shops", "&cThere aren't shops, please create one!");
        dataConfig("Messages.GUI_Too_Many_Shops", "&cThere're too many shops (more than 54 which's the limit supported)");
        dataConfig("Messages.GUI_Open_Success", "&aThe GUI got open with success!");
        dataConfig("Messages.Unknown_Pshop_SubCommand", "&cUnknown /pshop subcommand!");
        dataConfig("Messages.Unknown_Pshops_SubCommand", "&cUnknown /pshops subcommand");
        dataConfig("Messages.Specify_Shop_Owner", "&cPlease specify a shop owner!");
    }

    public FileConfiguration getFile(){
        return conf;
    }

}