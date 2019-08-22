package it.gabryca.playershop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEvent;

import java.util.Set;

public class listeners implements Listener {

        @EventHandler
        public void onClick(InventoryClickEvent e){

            Player p = (Player) e.getWhoClicked();
            Configuration config = Main.getInstance().getConfig();
            Configuration message = Main.getMessages();

            if (e.getCurrentItem() == null)
                return;

            if (e.getClickedInventory().getTitle().equals("§aPlayerShops"))
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().hasItemMeta()) {
                    double x = config.getDouble("shops." + e.getCurrentItem().getItemMeta().getDisplayName().substring(2) + ".position.X");
                    double y = config.getDouble("shops." + e.getCurrentItem().getItemMeta().getDisplayName().substring(2) + ".position.Y");
                    double z = config.getDouble("shops." + e.getCurrentItem().getItemMeta().getDisplayName().substring(2) + ".position.Z");
                    String worldname = config.getString("shops." + e.getCurrentItem().getItemMeta().getDisplayName().substring(2) + ".position.world");
                    World world = Main.getInstance().getServer().getWorld(worldname);
                    p.teleport(new Location(world, x, y, z));
                    p.sendMessage("§a" + message.getString("Messages.Shop-Teleport-Successful"));
                }
            }

        }

}
