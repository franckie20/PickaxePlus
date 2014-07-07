package com.franckie20.PickaxePlus;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ExplosivePickaxeListener implements Listener {

	private PickaxePlus plugin;
	
	public ExplosivePickaxeListener(PickaxePlus plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		final Player p = event.getPlayer();
		ItemStack item;
		Block current;
		item = p.getItemInHand();

		if (!p.hasPermission("pickaxeplus.use.explosivepickaxe")) {
			p.sendMessage(ChatColor.GRAY + "[PickaxePlus] " + "You are not allowed to use this!");
			event.setCancelled(true);
			p.getInventory().getItemInHand().setDurability((short) 0);
		}

		if (p.hasPermission("pickaxeplus.use.explosivepickaxe")) {
			if(event.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
				if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Explosive Pickaxe")) {
					Block target = event.getBlock();
					Block centre = target;
					Location loc = target.getLocation();
					List<String> breakable = plugin.getConfig().getStringList("breakable");

					for(String s : breakable) {
						if (target.getRelative(BlockFace.UP).getType() == Material.getMaterial(s) || target.getRelative(BlockFace.DOWN).getType() == Material.getMaterial(s)){
							p.getWorld().createExplosion(loc, 0);
							current = centre.getRelative(BlockFace.NORTH, 1);
			    			if(current.getType() == Material.getMaterial(s)) {
			    				current.breakNaturally();
			    			}
			        		current = centre.getRelative(BlockFace.SOUTH, 1);
			        		if(current.getType() == Material.getMaterial(s)) {
								current.breakNaturally();
							}
			        		current = centre.getRelative(BlockFace.SOUTH_EAST, 1);
			        		if(current.getType() == Material.getMaterial(s)) {
								current.breakNaturally();
							}
			        		current = centre.getRelative(BlockFace.EAST, 1);
			        		if(current.getType() == Material.getMaterial(s)) {
								current.breakNaturally();
							}
			        		target.breakNaturally();
			    		 }
						if (target.getRelative(BlockFace.NORTH).getType() == Material.getMaterial(s) || target.getRelative(BlockFace.SOUTH).getType() == Material.getMaterial(s)){
							p.getWorld().createExplosion(loc, 0);
							current = centre.getRelative(BlockFace.UP, 1);
			    			if(current.getType() == Material.getMaterial(s)) {
			    				current.breakNaturally();
			    			}
			        		current = centre.getRelative(BlockFace.DOWN, 1);
			        		if(current.getType() == Material.getMaterial(s)) {
								current.breakNaturally();
							}
			        		current = centre.getRelative(BlockFace.SOUTH_EAST, 1);
			        		if(current.getType() == Material.getMaterial(s)) {
								current.breakNaturally();
							}
			        		current = centre.getRelative(BlockFace.EAST, 1);
			        		if(current.getType() == Material.getMaterial(s)) {
								current.breakNaturally();
							}
			        		target.breakNaturally();
						}
					}
				}
			}
		}
	}
}
