package it.gabryca.playershop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GUI {

    int dimension = 0;
    private Player p;

    public GUI(Player p){
        this.p = p;
    }

    public ItemStack createButton(Material id, int amount, List<String> lore, String display) {

        ItemStack item = new ItemStack(id, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void open() {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (config.getConfigurationSection("shops") != null) {
            Set<String> shops = config.getConfigurationSection("shops").getKeys(false);
            int num = shops.size();
            while (dimension < num + 8) {
                dimension = dimension + 9;
            }
            Inventory inv = Bukkit.createInventory(null, dimension, "§aShops");
            for (String key : shops) {
                Set<String> number = config.getConfigurationSection("shops." + key).getKeys(false);
                for (String key2 : number) {
                    List<String> lore = new ArrayList<String>();
                    lore.add("§a" + message.getString("Messages.Click-to-teleport"));
                    String display = config.getString("shops." + key + "." + key2 + ".position.name") + " " + key2;
                    inv.addItem(createButton(Material.valueOf(config.getString("shops." + key + "." + key2 + ".position.block")), 1, lore, "§6" + display));
                }
            }
            this.p.openInventory(inv);
        } else {
            p.closeInventory();
            p.sendMessage(message.getString("Messages.Warn-NoShopsEverCreated"));
        }
    }
}
