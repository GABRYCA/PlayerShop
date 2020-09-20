package it.gabryca.playershop.gui;

import it.gabryca.playershop.PlayerShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;

public class Listeners implements Listener {

    private static Listeners instance;
    public static List<String> activeGui = new ArrayList<>();

    public Listeners(){}

    public static Listeners get() {
        if (instance == null) {
            instance = new Listeners();
        }
        return instance;
    }

    @EventHandler
    public void onGuiClosing(InventoryCloseEvent e){

        Player p = (Player) e.getPlayer();

        activeGui.remove(p.getName());
    }

    public void addToGUIBlocker(Player p){
        if(!activeGui.contains(p.getName()))
            activeGui.add(p.getName());
    }

    // InventoryClickEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Configuration config = PlayerShop.getInstance().getConfig();
        Configuration message = PlayerShop.getMessages();

        if (e.getCurrentItem() == null) {
            return;
        }

        if (!(e.getCurrentItem().hasItemMeta())) {
            return;
        }

        String title = e.getView().getTitle().substring(2);
        if (title == null){
            title = e.getInventory().getTitle().substring(2);
        }

        String shop = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
        String[] data = shop.split(" ");
        if (title.equalsIgnoreCase("PlayerShops")) {

            Bukkit.dispatchCommand(p,"pshop tp " + data[0] + " " + data[1]);

        } else if (title.equalsIgnoreCase("PlayerShops - My Shops")){

            if (e.getClick().isRightClick() && e.getClick().isShiftClick()){

                Bukkit.dispatchCommand(p, "pshop delete " + data[1]);

            } else {

                Bukkit.dispatchCommand(p, "pshop tp " + data[0] + " " + data[1]);

            }

        }
    }

}
