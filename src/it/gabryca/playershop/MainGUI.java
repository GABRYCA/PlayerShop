package it.gabryca.playershop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainGUI {

    private int dimension = 27;
    private Player p;

    public MainGUI(Player p){
        this.p = p;
    }

    private ItemStack createButton(Material id, int amount, List<String> lore, String display) {

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

        Inventory inv = Bukkit.createInventory(null,dimension,"§aPlayerShops");

        List<String> lore = new ArrayList<String>();
        lore.add("§a" + message.getString("Messages.Shop-Click-Open"));
        String display = "§6" + message.getString("Messages.Shop-Your");
        inv.setItem(11,createButton(Material.PAPER, 1, lore, "§6" + display));

        String display2 = "§6" + message.getString("Messages.Shop-Public");
        inv.setItem(15,createButton(Material.SIGN, 1, lore, "§6" + display2));

        this.p.openInventory(inv);
    }

}
