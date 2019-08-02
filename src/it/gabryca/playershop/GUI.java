package it.gabryca.playershop;

import org.bukkit.Bukkit;
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

    int y = 0;
    int x = 0;
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
        Set<String> shops = config.getConfigurationSection("shops").getKeys(false);
        int num = shops.size();
        while (dimension < num + 8) {
            dimension = dimension + 9;
        }
        Inventory inv = Bukkit.createInventory(null,dimension,"§aPlayerShops");
        for (String key : shops) {
            String display = config.getString("shops." + key + ".position.name");
            inv.addItem(createButton(Material.EMERALD_BLOCK, 1, null, "§6" + display));
        }

        this.p.openInventory(inv);
    }

}
