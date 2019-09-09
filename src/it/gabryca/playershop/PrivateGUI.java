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

public class PrivateGUI {

    int dimension = 54;
    private Player p;

    public PrivateGUI(Player p){
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
        String player = p.getDisplayName();

        int getLimit = config.getInt("Permissions.playershop-max-limit-default");
        for (int i = config.getInt("Permissions.playershop-max-limit-default"); i > 0; i--) {
            if (p.hasPermission("playershop.limit." + i)) {
                getLimit = i;
                break;
            }
        }

        Inventory inv = Bukkit.createInventory(null, dimension, "§6" + message.getString("Messages.Shop-Your"));

        if (config.getConfigurationSection("shops." + player) != null) {
            Set<String> shops = config.getConfigurationSection("shops." + player).getKeys(false);
            int num = config.getConfigurationSection("shops." + player).getKeys(false).size();
            for (String key : shops) {
                Set<String> number = config.getConfigurationSection("shops." + player).getKeys(false);
                for (String key2 : number) {
                    if (config.getString("shops." + player + "." + key2 + ".position.name").equals(player)) {
                        List<String> lore = new ArrayList<String>();
                        lore.add("§b" + message.getString("Messages.Shop-LeftClick-Logo"));
                        lore.add("§c" + message.getString("Messages.Shop-RightClick-Delete"));
                        String display = config.getString("shops." + player + "." + key2 + ".position.name") + " " + key2;
                        inv.addItem(createButton(Material.valueOf(config.getString("shops." + player + "." + key2 + ".position.block")), 1, lore, "§6" + display));
                        List<String> lore2 = new ArrayList<String>();
                        String display2 = message.getString("Messages.Shop-Add");
                        lore2.add("§7" + message.getString("Messages.Shop-Set-Here"));
                        inv.setItem(47, createButton(Material.EMERALD_BLOCK, 1, lore2, "§6" + display2));

                        List<String> lore3 = new ArrayList<String>();
                        String display3 = message.getString("Messages.Shop-Limit");
                        inv.setItem(49, createButton(Material.PAPER, 1, lore3, "§c" + display3 + " §6" + num + "/" + getLimit));

                        this.p.openInventory(inv);
                    }
                }
            }
        } else {

            List<String> lore2 = new ArrayList<String>();
            String display2 = message.getString("Messages.Shop-Add");
            lore2.add("§7" + message.getString("Messages.Shop-Set-Here"));
            inv.setItem(47, createButton(Material.EMERALD_BLOCK, 1, lore2, "§6" + display2));

            List<String> lore3 = new ArrayList<String>();
            String display3 = message.getString("Messages.Shop-Limit");
            inv.setItem(49, createButton(Material.PAPER, 1, lore3, "§c" + display3 + " §6" +  "0/" + getLimit));

            this.p.openInventory(inv);
        }
    }
}
