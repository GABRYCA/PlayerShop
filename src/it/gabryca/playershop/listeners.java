package it.gabryca.playershop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class listeners implements Listener {

        @EventHandler
        public void onClick(InventoryClickEvent e){

            if (e.getCurrentItem() == null)
                return;

            if (e.getClickedInventory().getTitle().equals("§aPlayerShops")){
                e.setCancelled(true);
            }

        }

}
