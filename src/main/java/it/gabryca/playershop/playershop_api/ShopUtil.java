package it.gabryca.playershop.playershop_api;

import it.gabryca.playershop.PlayerShop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ShopUtil {

    private static ShopUtil instance;

    public ShopUtil(){}

    public static ShopUtil get() {
        if (instance == null) {
            instance = new ShopUtil();
        }
        return instance;
    }

    /**
     * Using this you can add a shop to your config, but you'll need to give a shopLogoBlock and the Player
     */
    public void createShop(Optional<String> shopLogoBlock, Player p) {

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        int freeSlot = getFreeSlotShop(p);

        Material shopLogoMaterial = getMaterial(shopLogoBlock, p);

        if (shopLogoMaterial == null) return;

        if (freeSlot != 0){

            Location loc = (p.getLocation());
            String world = loc.getWorld().getName();
            double X = loc.getX();
            double Y = loc.getY();
            double Z = loc.getZ();

            config.set("shops." + p.getName() + "." + freeSlot + ".owner", p.getName());
            config.set("shops." + p.getName() + "." + freeSlot + ".shopLogo", shopLogoMaterial.name());
            config.set("shops." + p.getName() + "." + freeSlot + ".world", world);
            config.set("shops." + p.getName() + "." + freeSlot + ".X", X);
            config.set("shops." + p.getName() + "." + freeSlot + ".Y", Y);
            config.set("shops." + p.getName() + "." + freeSlot + ".Z", Z);
            PlayerShop.getInstance().saveConfig();
            p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Set_Successful")));
        }

    }

    public Material getMaterial(Optional<String> shopLogoBlock, CommandSender p) {

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        String shopLogoBlock2 = config.getString("Options.playershop-default-block");

        Material shopLogoMaterial = Material.matchMaterial(shopLogoBlock2);
        if (shopLogoMaterial == null){
            shopLogoMaterial = Material.getMaterial(shopLogoBlock2);
        }
        if (shopLogoMaterial == null){
            try {
                shopLogoMaterial = Material.valueOf(shopLogoBlock2);
            } catch (IllegalArgumentException e){
                p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Logo_Default_Not_A_Block")));
            }
        }

        Material shopLogoMaterial2 = null;
        if (shopLogoBlock.isPresent()) {
            String shopLogoBlock3 = shopLogoBlock.get();
            shopLogoMaterial2 = Material.matchMaterial(shopLogoBlock3);
            if (shopLogoMaterial2 == null) {
                shopLogoMaterial2 = Material.getMaterial(shopLogoBlock3);
            }
            if (shopLogoMaterial2 == null) {
                try {
                    shopLogoMaterial2 = Material.valueOf(shopLogoBlock3);
                } catch (IllegalArgumentException e){
                    p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Logo_Not_A_Block")));
                }
            }
        }

        if (shopLogoMaterial2 == null){
            return shopLogoMaterial;
        } else {
            return shopLogoMaterial2;
        }
    }

    /**
     * Edit a Shop Logo Block using the shopNumber, Sender and the shopLogoBlock
     * */
    public void shopLogoEdit(int shopNumber, CommandSender p, String shopLogoBlock){

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        Material shopLogoMaterial = getMaterial(Optional.ofNullable(shopLogoBlock), p);

        if (shopLogoMaterial == null) return;

        if (shopNumberCheck(Optional.of(shopNumber), p)) return;

        config.set("shops." + p.getName() + "." + shopNumber + ".shopLogo", shopLogoMaterial.name());
        PlayerShop.getInstance().saveConfig();
        p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Logo_Edited_With_Success")));
    }

    public boolean shopNumberCheck(Optional<Integer> shopNumber, CommandSender p) {

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        if (shopNumber.isPresent()) {
            if (config.getString("shops." + p.getName() + "." + shopNumber.get() + ".owner") == null) {
                p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Number_Not_Found")));
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a shop using the number or delete them all without specifing it, you'll also need the Player
     * */
    public void deletePlayerShop(CommandSender p, Optional<Integer> shopNumber){

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        if (shopNumber.isPresent()) {

            if (shopNumberCheck(shopNumber, p)) return;

            config.set("shops." + p.getName() + "." + shopNumber.get(), null);

        } else {

            config.set("shops." + p.getName(), null);

        }

        p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Deleted_With_Success")));
        PlayerShop.getInstance().saveConfig();
    }

    /**
     * Find a free slot to make a shop in the config and also check the limit in the config, if reached will return 0;
     * */
    public int getFreeSlotShop(Player p) {

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        int x = 1;

        // Find free slot
            while (config.getString("shops." + p.getName() + "." + x + ".owner") != null){
                x++;
            }

            if (x > Integer.parseInt(config.getString("Options.playershop-max-limit-default"))){
                p.sendMessage(PlayerShop.format(messages.getString("Messages.Exceed_Or_Dont_Have_Shops")));
                return 0;
            }
            return x;
    }

    /**
     * TP to a PlayerShop, need PlayerName of the owner and optional Number (if null will TP to the first one) and Player.
     * */
    public void shopTP(String playerName, Optional<Integer> shopNumber, Player p){

        FileConfiguration config = PlayerShop.getInstance().getConfig();
        FileConfiguration messages = PlayerShop.getMessages();

        if (!shopNumber.isPresent()){
            shopNumber = Optional.of(1);
        }

        if (config.getString("shops." + playerName + "." + shopNumber.get() + ".owner") == null){
            p.sendMessage(PlayerShop.format(messages.getString("Messages.Exceed_Or_Dont_Have_Shops")));
            return;
        }

        Location loc = (p.getLocation());
        World world2 = PlayerShop.getInstance().getServer().getWorld(loc.getWorld().getName());
        double X = loc.getX();
        double Y = loc.getY();
        double Z = loc.getZ();

        double x = config.getDouble("shops." + playerName + "." + shopNumber.get() + ".X");
        double y = config.getDouble("shops." + playerName + "." + shopNumber.get() + ".Y");
        double z = config.getDouble("shops." + playerName + "." + shopNumber.get() + ".Z");
        World world = PlayerShop.getInstance().getServer().getWorld(config.getString("shops." + playerName + "." + shopNumber.get() + ".world"));
        p.teleport(new Location(world ,x ,y ,z ));
        p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop-Teleport-Successful")));
        if (!p.isOnGround()){
            p.teleport(new Location(world2 ,X ,Y ,Z ));
            p.sendMessage(PlayerShop.format(messages.getString("Messages.Shop_Trap_Teleported_Back")));
        }
    }

}
