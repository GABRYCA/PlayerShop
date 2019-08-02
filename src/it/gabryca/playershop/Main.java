package it.gabryca.playershop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main config;

    public static Main getInstance(){
        return config;
    }

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[PlayerShop] Plugin abilitato con successo!");
        Bukkit.getPluginManager().registerEvents(new listeners(),this);
        getCommand("delshop").setExecutor(new delshop());
        getCommand("setshop").setExecutor(new setshop());
        getCommand("shopvisit").setExecutor(new shopvisit());
        getCommand("shops").setExecutor(new shops());
        this.saveDefaultConfig();
        config = this;
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[PlayerShop] Plugin disabilitato con successo!");
    }
}
