package it.gabryca.playershop.gui;

import it.gabryca.playershop.PlayerShop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PGUIPersonal extends SpigotGUIComponents {

    private final Player p;
    private int dimension = 9;

    public PGUIPersonal(Player p){
        this.p = p;
    }

    public void open() {

        // Create the inventory and set up the owner, dimensions or number of slots, and title
        Inventory inv = Bukkit.createInventory(null, dimension, PlayerShop.format("&3PlayerShops - My Shops"));

        Configuration messages = PlayerShop.getMessages();
        Configuration config = PlayerShop.getInstance().getConfig();

        if (guiBuilder(inv, messages, config)) return;

        // If the inventory is empty
        if (dimension == 0){
            p.sendMessage(PlayerShop.format(messages.getString("Messages.GUI_No_Shops")));
            p.closeInventory();
            return;
        }

        // If the dimension's too big, don't open the GUI
        if (dimension > 54){
            p.sendMessage(PlayerShop.format(messages.getString("Messages.GUI_Too_Many_Shops")));
            p.closeInventory();
            return;
        }

        // Open the inventory
        this.p.openInventory(inv);
        Listeners.get().addToGUIBlocker(p);
        p.sendMessage(PlayerShop.format(messages.getString("Messages.GUI_Open_Success")));
    }

    private boolean guiBuilder(Inventory inv, Configuration messages, Configuration config) {
        try {
            buttonsSetup(inv, messages, config);
        } catch (NullPointerException ex){
            p.sendMessage(PlayerShop.format("&cThere's a null value [broken]"));
            ex.printStackTrace();
            return true;
        }
        return false;
    }

    private void buttonsSetup(Inventory inv, Configuration messages, Configuration config) {

        int shops = 0;

        List<String> loreButton = createLore(
                "&3Click to visit.",
                "&cShift + Right-Click to delete.");

        try {

            for (String number : config.getConfigurationSection("shops." + p.getName()).getKeys(false)) {
                shops++;
                ItemStack shop = createButton(Material.valueOf(config.getString("shops." + p.getName() + "." + number + ".shopLogo")), 1, loreButton, PlayerShop.format("&3" + config.getString("shops." + p.getName() + "." + number + ".owner") + " " + number));
                inv.addItem(shop);
            }
        } catch (NullPointerException e){
            dimension = 0;
            return;
        }

        // Get the dimensions and if needed increases them
        dimension = (int) Math.ceil(shops / 9D) * 9;

    }
}
