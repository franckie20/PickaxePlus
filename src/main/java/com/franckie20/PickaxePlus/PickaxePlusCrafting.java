package com.franckie20.PickaxePlus;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxePlusCrafting implements Listener {

	private String PREFIX = ChatColor.GRAY + "[PickaxePlus] ";

	public PickaxePlusCrafting(PickaxePlus plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemCraft(CraftItemEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}

		Player p = (Player) event.getWhoClicked();

		ItemStack item = event.getRecipe().getResult();

		ItemStack fire = setMeta(
				new ItemStack(Material.FIRE),
				ChatColor.RED + "Craft denied",
				Arrays.asList(ChatColor.GRAY + "You are not allowed to craft this!"));

		if (item != null) {
			ItemMeta meta = item.getItemMeta();

			if (meta != null) {
				// This part is probably never null...

				String name = meta.getDisplayName();
				if (name != null) {
					if (name.equals(ChatColor.BLUE + "Speed Pickaxe")) {
						if (p.hasPermission("pickaxeplus.craft.speedpickaxe")) {
							event.setCurrentItem(fire);
							event.setCancelled(true);
							p.sendMessage(PREFIX + "You are not allowed to craft, " + name.toString());
						}
					}

					else if (name.equals(ChatColor.DARK_PURPLE
							+ "Obsidian Pickaxe")) {
						if (!p.hasPermission("pickaxeplus.craft.obsdianpickaxe")) {
							event.setCurrentItem(fire);
							event.setCancelled(true);
							p.sendMessage(PREFIX + "You are not allowed to craft, " + name.toString());
						}
					}

					else if (name
							.equals(ChatColor.DARK_PURPLE + "Obsidian Axe")) {
						if (!p.hasPermission("pickaxeplus.craft.obsidianaxe")) {
							event.setCurrentItem(fire);
							event.setCancelled(true);
							p.sendMessage(PREFIX
									+ "You are not allowed to craft, "
									+ name.toString());
						}
					}

					else if (name.equals(ChatColor.RED + "Explosive Pickaxe")) {
						if (!p.hasPermission("pickaxeplus.craft.explosivepickaxe")) {
							event.setCurrentItem(fire);
							event.setCancelled(true);
							p.sendMessage(PREFIX + "You are not allowed to craft, " + name.toString());
						}
					}

					else if (name.equals(ChatColor.GREEN + "Smelters Pickaxe")) {
						if (!p.hasPermission("pickaxeplus.craft.smelterspickaxe")) {
							event.setCurrentItem(fire);
							event.setCancelled(true);
							p.sendMessage(PREFIX + "You are not allowed to craft, " + name.toString());
						}
					}
				}
			}
		}
	}

	public ItemStack setMeta(ItemStack material, String name, List<String> lore) {
		if (((material == null) || material.getType() == Material.AIR)
				|| (name == null && lore == null)) {
			return null;
		}

		ItemMeta im = material.getItemMeta();

		if (name != null)
			im.setDisplayName(name);
		if (lore != null)
			im.setLore(lore);

		material.setItemMeta(im);
		return material;
	}
}
