package com.franckie20.PickaxePlus;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class SpeedPickaxeListener implements Listener {
	
	private String PREFIX = ChatColor.GRAY + "[PickaxePlus] ";
	private HashSet<String> cooldowns = new HashSet<String>();
	
	public SpeedPickaxeListener(PickaxePlus plugin, int counter) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
    }
	
	private final PickaxePlus plugin;

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		final Player p = event.getPlayer();
			if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
				if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
						.equals(ChatColor.BLUE + "Speed Pickaxe")) {
					if(!p.hasPermission("pickaxeplus.use.speedpick")) {
						p.sendMessage(PREFIX + "You are not allowed to use this!");
						event.setCancelled(true);
					}
				}
			}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		
		if(p.hasPermission("pickaxeplus.use.speedpick")) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR) {
				if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
					if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
							.equals(ChatColor.BLUE + "Speed Pickaxe")) {
					
						if (!cooldowns.contains(p.getName())) {
							BukkitScheduler startEnchant = Bukkit.getServer().getScheduler();
							startEnchant.scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 5);
									p.sendMessage(PREFIX + "Speed Pickaxe ability enabled!");
									cooldowns.add(p.getName());
								}
							}, 1L);
					
							BukkitScheduler endEnchant = Bukkit.getServer().getScheduler();
							endEnchant.scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.getItemInHand().removeEnchantment(Enchantment.DIG_SPEED);
									p.sendMessage(PREFIX + "Speed Pickaxe ability disabled!");
								}
							}, plugin.getConfig().getInt("speed-buff-duration"));
						
							new BukkitRunnable() {
								public void run() {
									cooldowns.remove(p.getName());
									p.sendMessage(PREFIX + "Speed Pickaxe ability is now refreshed!");
								}
							}.runTaskLater(plugin, plugin.getConfig().getInt("cooldown"));
						}
						if(cooldowns.contains(p.getName())) {
							p.sendMessage(PREFIX + "Speed Pickaxe ability is on cooldown!");
						}
					}
				}
			} 
		}
		
		if(p.hasPermission("pickaxeplus.use.speedpick")) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
					if (p.getInventory().getItemInHand().getItemMeta().getDisplayName()
							.equals(ChatColor.BLUE + "Speed Pickaxe")) {
					
						if (!cooldowns.contains(p.getName())) {
							BukkitScheduler startEnchant = Bukkit.getServer().getScheduler();
							startEnchant.scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.getItemInHand().addEnchantment(Enchantment.DIG_SPEED, 5);
									p.sendMessage(PREFIX + "Speed Pickaxe ability enabled!");
									cooldowns.add(p.getName());
								}
							}, 1L);
					
							BukkitScheduler endEnchant = Bukkit.getServer().getScheduler();
							endEnchant.scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run() {
									p.getItemInHand().removeEnchantment(Enchantment.DIG_SPEED);
									p.sendMessage(PREFIX + "Speed Pickaxe ability disabled!");
								}
							}, plugin.getConfig().getInt("speed-buff-duration"));
						
							new BukkitRunnable() {
								public void run() {
									cooldowns.remove(p.getName());
									p.sendMessage(PREFIX + "Speed Pickaxe ability is now refreshed!");
								}
							}.runTaskLater(plugin, plugin.getConfig().getInt("cooldown"));
						}
						if(cooldowns.contains(p.getName())) {
							p.sendMessage(PREFIX + "Speed Pickaxe ability is on cooldown!");
						}
					}
				}
			}
		}
	}
}
