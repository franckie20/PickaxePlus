package com.franckie20.PickaxePlus;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class SmeltersPickaxeListener implements Listener {
	
	private int fortune = 0;
	
	public SmeltersPickaxeListener(PickaxePlus plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

	public int quantityDropped(Random rnd) {
		return 1;
	}
	
	public int quantityDroppedWithBonus() {
		Random random = new Random();
		
        if (fortune > 0) {
        	
        	int j = random.nextInt(fortune + 2) - 1;
        	if (j < 0) {
                j = 0;
            }
            return quantityDropped(random) * (j + 1);
            
        } else {
            return quantityDropped(random);
        }
    }

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		
		final Player p = event.getPlayer();
		
		if(p.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
			if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
				fortune = 1;
			}
			if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
				fortune = 2;
			}
			if(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
				fortune = 3;
			}
		}
		
		if(!p.hasPermission("pickaxeplus.use.smelterspickaxe")) {
			p.sendMessage(ChatColor.GRAY + "[PickaxePlus] " + "You are not allowed to use this!");
			event.setCancelled(true);
			p.getInventory().getItemInHand().setDurability((short) 0);
		}
		
		if(p.hasPermission("pickaxe.use.smelterspickaxe")) {
			if(event.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
				Block block = event.getBlock();
			
				if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Smelters Pickaxe")) {
					if(event.getBlock().getType() == Material.IRON_ORE) {
						block.setType(Material.AIR);
						block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT, quantityDroppedWithBonus()));
						event.setExpToDrop(1);
						event.setCancelled(true); // Cancel the event because you've done it yourself.
					}
					if(event.getBlock().getType() == Material.GOLD_ORE) {
						block.setType(Material.AIR);
						block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT, quantityDroppedWithBonus()));
						event.setExpToDrop(2);
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
