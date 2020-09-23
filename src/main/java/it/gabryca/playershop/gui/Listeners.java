package it.gabryca.playershop.gui;

import it.gabryca.playershop.PlayerShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
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

    // Cancel the events of the active GUI opened from the player
    private void activeGuiEventCanceller(Player p, InventoryClickEvent e){
        if(activeGui.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    // InventoryClickEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Configuration config = PlayerShop.getInstance().getConfig();
        Configuration message = PlayerShop.getMessages();

        // Get action of the Inventory from the event
        InventoryAction action = e.getAction();

        // If an action equals one of these, and the inventory is open from the player equals
        // one of the Prison Title, it'll cancel the event
        if (action.equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) || action.equals(InventoryAction.HOTBAR_SWAP) ||
                action.equals(InventoryAction.HOTBAR_MOVE_AND_READD) || action.equals(InventoryAction.NOTHING) ||
                action.equals(InventoryAction.CLONE_STACK) || action.equals(InventoryAction.COLLECT_TO_CURSOR) ||
                action.equals(InventoryAction.DROP_ONE_SLOT) || action.equals(InventoryAction.DROP_ONE_CURSOR) ||
                action.equals(InventoryAction.DROP_ALL_SLOT) || action.equals(InventoryAction.DROP_ALL_CURSOR) ||
                action.equals(InventoryAction.PICKUP_ALL) || action.equals(InventoryAction.PICKUP_HALF) ||
                action.equals(InventoryAction.PICKUP_ONE) || action.equals(InventoryAction.PICKUP_SOME) ||
                action.equals(InventoryAction.PLACE_ALL) || action.equals(InventoryAction.PLACE_ONE) ||
                action.equals(InventoryAction.PLACE_SOME) || action.equals(InventoryAction.SWAP_WITH_CURSOR) ||
                action.equals(InventoryAction.UNKNOWN)) {
            activeGuiEventCanceller(p, e);
        }

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

        if(activeGui.contains(p.getName())) {
            e.setCancelled(true);
        }

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
