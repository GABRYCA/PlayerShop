package it.gabryca.playershop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main config;

    public static Main getInstance(){
        return config;
    }

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[PlayerShop] Plugin enabled with success!");
        Bukkit.getPluginManager().registerEvents(new listeners(),this);
        getCommand("delshop").setExecutor(new delshop());
        getCommand("setshop").setExecutor(new setshop());
        getCommand("shopvisit").setExecutor(new shopvisit());
        getCommand("shops").setExecutor(new shops());
        getCommand("Playershop").setExecutor(new Playershop());
        getCommand("shoplogo").setExecutor(new shoplogo());
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

}
