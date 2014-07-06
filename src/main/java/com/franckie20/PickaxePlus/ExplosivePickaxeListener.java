package com.franckie20.PickaxePlus;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ExplosivePickaxeListener implements Listener {

	public ExplosivePickaxeListener(PickaxePlus plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent  e2) {
		final Player p = e2.getPlayer();
		ItemStack item;
   	 	Block current;
   	 	item = p.getItemInHand();
		
		if(!p.hasPermission("pickaxeplus.use.explosivepickaxe")) {
			p.sendMessage(ChatColor.GRAY + "[PickaxePlus] " + "You are not allowed to use this!");
			e2.setCancelled(true);
			p.getInventory().getItemInHand().setDurability((short) 0);
		}
		
		if(p.hasPermission("pickaxeplus.use.explosivepickaxe")) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Explosive Pickaxe"))
	    	 {
				Block target = e2.getClickedBlock();
	    		Block centre = target;
	    		 
	    	
	    		 BlockFace tempFace = e2.getBlockFace();
	    		 if (tempFace.equals(BlockFace.UP) || tempFace.equals(BlockFace.DOWN))
	    		 {
	    			 current = centre.getRelative(BlockFace.NORTH, 1);
	        		 current.breakNaturally();
	        		 current = centre.getRelative(BlockFace.NORTH_EAST, 1);
	        		 current.breakNaturally();
	        		 current = centre.getRelative(BlockFace.SOUTH, 1);
	        		 current.breakNaturally();
	        		 current = centre.getRelative(BlockFace.SOUTH_EAST, 1);
	        		 current.breakNaturally();
	        		 current = centre.getRelative(BlockFace.EAST, 1);
	        		 current.breakNaturally();
	        		 target.breakNaturally();
	    		 }
	    		 else if (tempFace.equals(BlockFace.NORTH) || tempFace.equals(BlockFace.SOUTH))
	    		 {
	    			 current = centre.getRelative(BlockFace.WEST, 1);
	    			 current.breakNaturally();
	    			 centre = target.getRelative(BlockFace.UP, 1);
					 centre.breakNaturally(); 
	    			 current = centre.getRelative(BlockFace.WEST, 1);
	    			 current.breakNaturally();
	    			 centre = target.getRelative(BlockFace.DOWN, 1);
					 centre.breakNaturally();
	    			 current = centre.getRelative(BlockFace.WEST, 1);
	    			 current.breakNaturally();
	    			 target.breakNaturally();
	    			 
	    		 }
	    		 else if (tempFace.equals(BlockFace.EAST) || tempFace.equals(BlockFace.WEST))
	    		 {
	    			 current = centre.getRelative(BlockFace.NORTH, 1);
	    			 current.breakNaturally();
	    			 centre = target.getRelative(BlockFace.UP, 1);
					 centre.breakNaturally(); 
	    			 current = centre.getRelative(BlockFace.NORTH, 1);
	    			 current.breakNaturally();
	    			 centre = target.getRelative(BlockFace.DOWN, 1);
					 centre.breakNaturally();
					 current = centre.getRelative(BlockFace.NORTH, 1);
	    			 current.breakNaturally();
	    			 target.breakNaturally();
	    			 
	    		 }
	    	 }
		}
	}
}
