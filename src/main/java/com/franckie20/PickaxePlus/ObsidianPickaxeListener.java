package com.franckie20.PickaxePlus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ObsidianPickaxeListener implements Listener {

	public ObsidianPickaxeListener(PickaxePlus plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		final Player p = event.getPlayer();
			if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
				if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
						.equals(ChatColor.DARK_PURPLE + "Obsidian Pickaxe")) {
					if(!p.hasPermission("pickaxeplus.use.obsidianpickaxe")) {
						p.sendMessage(ChatColor.GRAY + "[PickaxePlus] " + "You are not allowed to use this!");
						event.setCancelled(true);
					}
				}
			}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		
		if(p.hasPermission("pickaxeplus.use.obsidianpickaxe")) {
			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
					if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
							.equals(ChatColor.DARK_PURPLE + "Obsidian Pickaxe")) {
						p.getInventory().getItemInHand().setDurability((short) 0);
					}
				}
			}
		}
	}
}
