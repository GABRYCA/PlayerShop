package it.gabryca.playershop;

import it.gabryca.playershop.commands.ShopCommands;
import it.gabryca.playershop.commands.ShopGUICommands;
import it.gabryca.playershop.configs.MessagesYML;
import it.gabryca.playershop.gui.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerShop extends JavaPlugin {

    private static PlayerShop config;

    public static PlayerShop getInstance(){
        return config;
    }

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[PlayerShop] Plugin enabled with success!");
        Bukkit.getPluginManager().registerEvents(new Listeners(),this);
        getCommand("pshops").setExecutor(new ShopGUICommands());
        getCommand("pshop").setExecutor(new ShopCommands());
        this.saveDefaultConfig();
        config = this;
        this.saveConfig();
        new MessagesYML();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[PlayerShop] Plugin disabled with success!");
    }

    public static FileConfiguration getMessages(){
        MessagesYML messages = new MessagesYML();
        return messages.getFile();
    }

    public static String format(String format){
        return ChatColor.translateAlternateColorCodes('&', format);
    }

}
