package it.gabryca.playershop;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class listeners implements Listener {

        @EventHandler
        public void onClick(InventoryClickEvent e) {

            int FadeIn = 20;
            int Duration = 200;
            int FadeOut = 20;
            Player p = (Player) e.getWhoClicked();
            Configuration config = Main.getInstance().getConfig();
            Configuration message = Main.getMessages();

            if (e.getCurrentItem() == null) {
                return;
            }

            if (e.getView().getTitle().equals("§6" + message.getString("Messages.Shop-Your"))){
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().hasItemMeta()) {
                        String Shop = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                        String[] data = e.getCurrentItem().getItemMeta().getDisplayName().substring(2).split(" ");
                        if (!(Shop.equals(message.getString("Messages.Shop-Add")))) {
                            if (e.isLeftClick()) {
                                p.closeInventory();
                                p.sendTitle(message.getString("Messages.Shop-None"), message.getString("Messages.Shop-GoTo"), FadeIn, Duration, FadeOut);
                            } else if (e.isRightClick()){
                                p.closeInventory();
                                PrivateGUI privategui = new PrivateGUI(p);
                                privategui.open();
                                if (config.getString("shops." + data[0] + "." + data[1] + ".position.name") != null) {
                                    config.set("shops." + data[0] + "." + data[1] + ".position", null);
                                    config.set("shops." + data[0] + "." + data[1], null);
                                    config.set("shops." + data[0] + "." + data[1] + ".position.name", null);
                                    config.set("shops." + data[0] + "." + data[1] + ".position.X", null);
                                    config.set("shops." + data[0] + "." + data[1] + ".position.Y", null);
                                    config.set("shops." + data[0] + "." + data[1] + ".position.Z", null);
                                    config.set("shops." + data[0] + "." + data[1] + ".position.world", null);
                                    config.set("shops." + data[0], null);
                                    Main.getInstance().saveConfig();
                                    p.sendMessage("§a" + message.getString("Messages.Shop-Deleted-Successful"));
                                    p.closeInventory();
                                    privategui.open();
                                } else {
                                    p.sendMessage("§c" + message.getString("Messages.Warn-NoShops"));
                                }
                            }
                        } else {
                            p.closeInventory();
                            PrivateGUI privategui = new PrivateGUI(p);
                            privategui.open();
                            int number = 1;
                            while (config.getString("shops." + p.getName() + "." + number) != null){
                                number++;
                            }
                                if (p.hasPermission(config.getString("Permissions.setshop"))){
                                    if (!(p.isFlying())) {
                                        for (int i = config.getInt("Permissions.playershop-max-limit-default"); i > 0; i--) {
                                            if (p.hasPermission("playershop.limit." + i)) {
                                                int getLimit = i;
                                                if ((config.getString("shops." + p.getName() + "." + (number-1) + ".position.name")) != null){
                                                    int num = config.getConfigurationSection("shops." + p.getName()).getKeys(false).size();
                                                    if (!(num >= getLimit)) {
                                                        Location loc = (p.getLocation());
                                                        String world = loc.getWorld().getName();
                                                        double X = loc.getX();
                                                        double Y = loc.getY();
                                                        double Z = loc.getZ();
                                                        config.set("shops." + p.getName() + "." + number + ".position.block", "EMERALD_BLOCK");
                                                        config.set("shops." + p.getName() + "." + number + ".position.name", p.getName());
                                                        config.set("shops." + p.getName() + "." + number + ".position.X", X);
                                                        config.set("shops." + p.getName() + "." + number + ".position.Y", Y);
                                                        config.set("shops." + p.getName() + "." + number + ".position.Z", Z);
                                                        config.set("shops." + p.getName() + "." + number + ".position.world", world);
                                                        config.set("shops." + p.getName() + "." + number + ".position.number", number);
                                                        Main.getInstance().saveConfig();
                                                        p.sendMessage("§a" + message.getString("Messages.Shop-Set-Successful"));
                                                        privategui.open();
                                                    } else {
                                                        p.sendMessage("§c" + message.getString("Messages.Shop-Limit-Reached") + " §7[§c" + message.getString("Messages.Shop-Limit") + "§7]");
                                                    }
                                                } else {
                                                    Location loc = (p.getLocation());
                                                    String world = loc.getWorld().getName();
                                                    double X = loc.getX();
                                                    double Y = loc.getY();
                                                    double Z = loc.getZ();
                                                    config.set("shops." + p.getName() + "." + number + ".position.block", "EMERALD_BLOCK");
                                                    config.set("shops." + p.getName() + "." + number + ".position.name", p.getName());
                                                    config.set("shops." + p.getName() + "." + number + ".position.X", X);
                                                    config.set("shops." + p.getName() + "." + number + ".position.Y", Y);
                                                    config.set("shops." + p.getName() + "." + number + ".position.Z", Z);
                                                    config.set("shops." + p.getName() + "." + number + ".position.world", world);
                                                    config.set("shops." + p.getName() + "." + number + ".position.number", number);
                                                    Main.getInstance().saveConfig();
                                                    p.sendMessage("§a" + message.getString("Messages.Shop-First-Set-Successful"));
                                                    privategui.open();
                                                }
                                                break;
                                            }
                                        }
                                    } else {
                                        p.sendMessage("§c" + message.getString("Messages.Warn-NotOnGround"));
                                    }
                                } else {
                                    p.sendMessage("§c" + message.getString("Messages.Warn-permission") + " " +  config.getString("Permissions.setshop"));
                                }
                        }
                    }
                }
            }

            if (e.getView().getTitle().equals("§aPlayerShops")){
                if (e.getCurrentItem() != null){
                    if (e.getCurrentItem().hasItemMeta()){
                        String Shop = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                        if (Shop.equals("§6" + message.getString("Messages.Shop-Public"))) {
                            GUI guishop = new GUI(p);
                            guishop.open();
                        } else if (Shop.equals("§6" + message.getString("Messages.Shop-Your"))){
                            PrivateGUI privategui = new PrivateGUI(p);
                            privategui.open();
                        }
                    }
                }
            }

            if (e.getView().getTitle().equals("§aShops")){
                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().hasItemMeta()) {
                            String[] data = e.getCurrentItem().getItemMeta().getDisplayName().substring(2).split(" ");
                            double x = config.getDouble("shops." + data[0] + "." + data[1] + ".position.X");
                            double y = config.getDouble("shops." + data[0] + "." + data[1] + ".position.Y");
                            double z = config.getDouble("shops." + data[0] + "." + data[1] + ".position.Z");
                            String worldname = config.getString("shops." + data[0] + "." + data[1] + ".position.world");
                            World world = Main.getInstance().getServer().getWorld(worldname);
                            p.teleport(new Location(world, x, y, z));
                            p.sendMessage("§a" + message.getString("Messages.Shop-Teleport-Successful"));
                    }
                }
            }

        }

}
