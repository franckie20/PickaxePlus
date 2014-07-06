package com.franckie20.PickaxePlus;

import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PickaxePlus extends JavaPlugin {
		
	private static Plugin plugin;
	
	private String LOG_PREFIX = "[PickaxePlus]";
	private final Logger log = Logger.getLogger("Minecraft");
	
	public void log (String m){
		log.info(LOG_PREFIX + m);
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		plugin = this;
		
		// Save a copy of the default config.yml if one is not there
        this.saveDefaultConfig();
		
		// Adding recipes
		addRecipeSmelterPickaxe();
		addRecipeObsidianAxe();
		addRecipeObsidianPickaxe();
		addRecipeExplosivePickaxe();
		addRecipeSpeedPickaxe();
		
		// Register our commands
        getCommand("pickaxeplus").setExecutor(new PickaxePlusCommand(this));
        
        // Register listener
        new PickaxePlusCrafting(this);
        new SpeedPickaxeListener(this, 10);
        new SmeltersPickaxeListener(this);
        new ObsidianPickaxeListener(this);
        new ObsidianAxeListener(this);
        new ExplosivePickaxeListener(this);
        log("has been enabled!");
	}
	
	public void onReload() {
		this.reloadConfig();
	}
	
	public void onDisable() {
		plugin = null;
		log("has been disabled!");
		this.getServer().clearRecipes();
	}
	
	public void addRecipeSmelterPickaxe() {
		ItemStack newPick = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta sp = newPick.getItemMeta();
		sp.setDisplayName(ChatColor.GREEN + "Smelters Pickaxe");
		sp.setLore(Arrays.asList(ChatColor.GRAY + "Auto smelt Iron + Gold"));
		newPick.setItemMeta(sp);
		
		ShapedRecipe smelterPick = new ShapedRecipe(newPick);
		smelterPick.shape("FFF", " B ", " B ");
		smelterPick.setIngredient('F', Material.FURNACE);
		smelterPick.setIngredient('B', Material.BLAZE_ROD);
		this.getServer().addRecipe(smelterPick);
	}
	
	public void addRecipeObsidianAxe() {
		ItemStack newAxe = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta sp = newAxe.getItemMeta();
		sp.setDisplayName(ChatColor.DARK_PURPLE + "Obsidian Axe");
		sp.setLore(Arrays.asList(ChatColor.GRAY + "Unbreakable Axe"));
		newAxe.setItemMeta(sp);
		
		ShapedRecipe obsidianAxe = new ShapedRecipe(newAxe);
		obsidianAxe.shape("OO ", "OB ", " B ");
		obsidianAxe.setIngredient('O', Material.OBSIDIAN);
		obsidianAxe.setIngredient('B', Material.BLAZE_ROD);
		this.getServer().addRecipe(obsidianAxe);
	}
	
	public void addRecipeObsidianPickaxe() {
		ItemStack newPick = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta sp = newPick.getItemMeta();
		sp.setDisplayName(ChatColor.DARK_PURPLE + "Obsidian Pickaxe");
		sp.setLore(Arrays.asList(ChatColor.GRAY + "Unbreakable Pickaxe"));
		newPick.setItemMeta(sp);
		
		ShapedRecipe obsidianPickaxe = new ShapedRecipe(newPick);
		obsidianPickaxe.shape("OOO", " B ", " B ");
		obsidianPickaxe.setIngredient('O', Material.OBSIDIAN);
		obsidianPickaxe.setIngredient('B', Material.BLAZE_ROD);
		this.getServer().addRecipe(obsidianPickaxe);
	}
	
	public void addRecipeExplosivePickaxe() {
		ItemStack newPick = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta sp = newPick.getItemMeta();
		sp.setDisplayName(ChatColor.RED + "Explosive Pickaxe");
		sp.setLore(Arrays.asList(ChatColor.GRAY + "Small Explosion"));
		newPick.setItemMeta(sp);
		
		ShapedRecipe explosionPick = new ShapedRecipe(newPick);
		explosionPick.shape("TTT", " B ", " B ");
		explosionPick.setIngredient('T', Material.TNT);
		explosionPick.setIngredient('B', Material.BLAZE_ROD);
		this.getServer().addRecipe(explosionPick);
	}
	
	public void addRecipeSpeedPickaxe() {
		ItemStack newPick = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta sp = newPick.getItemMeta();
		sp.setDisplayName(ChatColor.BLUE + "Speed Pickaxe");
		sp.setLore(Arrays.asList(ChatColor.GRAY + "Speedy Pick"));
		newPick.setItemMeta(sp);
		
		ShapedRecipe speedPick = new ShapedRecipe(newPick);
		speedPick.shape("SSS", " B ", " B ");
		speedPick.setIngredient('S', Material.SUGAR);
		speedPick.setIngredient('B', Material.BLAZE_ROD);
		this.getServer().addRecipe(speedPick);
	}
}
