package com.franckie20.PickaxePlus;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxePlusCommand implements CommandExecutor {

	private PickaxePlus plugin;

	private String PREFIX = ChatColor.GRAY + "[PickaxePlus] ";

	public PickaxePlusCommand(PickaxePlus plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] split) {

		Player p = (Player) sender;

		if (!(sender instanceof Player)) {
			return false;
		}

		if (label.equalsIgnoreCase("pickaxeplus")) {
			if (split.length == 0) {
				sender.sendMessage("");
				sender.sendMessage("");
				sender.sendMessage(PREFIX + "Usage: /pickaxeplus help");
				return true;
			}

			if (split.length == 1) {
				if (split[0].equalsIgnoreCase("help")) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.RED + "--------" + ChatColor.WHITE
							+ "Help" + ChatColor.RED + "--------");
					p.sendMessage(ChatColor.WHITE + "/Pickaxeplus help");
					p.sendMessage(ChatColor.WHITE
							+ "/Pickaxeplus give <player> [amount] [item]");
					p.sendMessage(ChatColor.WHITE + "/Pickaxeplus give [item]");
					p.sendMessage(ChatColor.WHITE + "/Pickaxeplus reload");
					p.sendMessage(ChatColor.WHITE + "/Pickaxeplus items");
					return true;
				}

				if(p.hasPermission("pickaxeplus.reload")) {
					if (split[0].equalsIgnoreCase("reload")) {
						plugin.reloadConfig();
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage(PREFIX + "Config reloaded!");
						return true;
					}
				} else {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(PREFIX + "You don't have permission to use that command!");
					return true;
				}

				if (split[0].equalsIgnoreCase("items")) {
					List<String> items = plugin.getConfig().getStringList(
							"items");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "--------" + ChatColor.WHITE
							+ "Items" + ChatColor.BLUE + "--------");
					for (String s : items) {
						sender.sendMessage(s);
					}
					return true;
				}

				if (split[0].equalsIgnoreCase("give")) {
					plugin.reloadConfig();
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(PREFIX
							+ "Usage /pickaxeplus give <player> [amount] [item]");
					p.sendMessage(PREFIX + "Usage /pickaxeplus give [item]");
					return true;
				}

				else {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(PREFIX + "Usage /pickaxeplus help");
					return true;
				}
			}

			if (split.length == 2) {

				if (p.hasPermission("pickaxeplus.give.self")) {
					if (split[0].equalsIgnoreCase("give")) {
						if (split[1].equalsIgnoreCase("smelterspick")) {
							ItemStack newSP = setMeta(new ItemStack(
									Material.DIAMOND_PICKAXE), ChatColor.GREEN
									+ "Smelters Pickaxe",
									Arrays.asList(ChatColor.GRAY
											+ "Auto smelt Iron + Gold"));
							p.getInventory().addItem(newSP);
							return true;
						}

						if (split[1].equalsIgnoreCase("obsidianpick")) {
							ItemStack newOP = setMeta(new ItemStack(
									Material.DIAMOND_PICKAXE),
									ChatColor.DARK_PURPLE + "Obsidian Pickaxe",
									Arrays.asList(ChatColor.GRAY
											+ "Unbreakable Pickaxe"));
							p.getInventory().addItem(newOP);
							return true;
						}

						if (split[1].equalsIgnoreCase("obsidianaxe")) {
							ItemStack newOA = setMeta(new ItemStack(
									Material.DIAMOND_AXE),
									ChatColor.DARK_PURPLE + "Obsidian Axe",
									Arrays.asList(ChatColor.GRAY
											+ "Unbreakable Axe"));
							p.getInventory().addItem(newOA);
							return true;
						}

						if (split[1].equalsIgnoreCase("explosivepick")) {
							ItemStack newEP = setMeta(new ItemStack(
									Material.DIAMOND_PICKAXE), ChatColor.RED
									+ "Explosive Pickaxe",
									Arrays.asList(ChatColor.GRAY
											+ "Small Explosion"));
							p.getInventory().addItem(newEP);
							return true;
						}

						if (split[1].equalsIgnoreCase("speedpick")) {
							ItemStack newSP = setMeta(new ItemStack(
									Material.DIAMOND_PICKAXE), ChatColor.BLUE
									+ "Speed Pickaxe",
									Arrays.asList(ChatColor.GRAY
											+ "Speedy Pickaxe"));
							p.getInventory().addItem(newSP);
							return true;
						}
					}
					if (!split[1].equals("speedpick")
							|| !split[1].equals("explosivepick")
							|| !split[1].equals("obsidianaxe")
							|| !split[1].equals("obsidianpick")
							|| !split[1].equals("smelterspick")) {
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage(PREFIX
								+ "Item with that name doesn't exist!");
						return true;
					}
				} else {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(PREFIX
							+ "You don't have permission to use that command!");
					return true;
				}
			}

			if (split.length == 4) {
				if (p.hasPermission("pickaxeplus.give.other")) {
					if (split[0].equalsIgnoreCase("give")) {
						Player target = (Bukkit.getServer().getPlayer(split[1]));

						if (target != null) {
							if (split[3].equalsIgnoreCase("smelterspick")) {
								ItemStack newSP = setMeta(new ItemStack(
										Material.DIAMOND_PICKAXE),
										ChatColor.GREEN + "Smelters Pickaxe",
										Arrays.asList(ChatColor.GRAY
												+ "Auto smelt Iron + Gold"));
								target.getInventory().addItem(newSP);
								target.sendMessage("");
								target.sendMessage("");
								target.sendMessage(PREFIX + "You received a "
										+ split[3]);

								if (split[2] != null) {
									int amount = Integer.parseInt(split[2]);
									newSP.setAmount(amount);
								}
								return true;
							}
							
							if (split[3].equalsIgnoreCase("speedpick")) {
								ItemStack newSP = setMeta(new ItemStack(
										Material.DIAMOND_PICKAXE),
										ChatColor.BLUE + "Speed Pickaxe",
										Arrays.asList(ChatColor.GRAY
												+ "Speedy Pickaxe"));
								target.getInventory().addItem(newSP);
								target.sendMessage("");
								target.sendMessage("");
								target.sendMessage(PREFIX + "You received a "
										+ split[3]);

								if (split[2] != null) {
									int amount = Integer.parseInt(split[2]);
									newSP.setAmount(amount);
								}
								return true;
							}
							
							if (split[3].equalsIgnoreCase("obsidianpick")) {
								ItemStack newOP = setMeta(new ItemStack(
										Material.DIAMOND_PICKAXE),
										ChatColor.DARK_PURPLE
												+ "Obsidian Pickaxe",
										Arrays.asList(ChatColor.GRAY
												+ "Unbreakable Pickaxe"));
								target.getInventory().addItem(newOP);
								target.sendMessage("");
								target.sendMessage("");
								target.sendMessage(PREFIX + "You received a "
										+ split[3]);

								if (split[2] != null) {
									int amount = Integer.parseInt(split[2]);
									newOP.setAmount(amount);
								}
								return true;
							}

							if (split[3].equalsIgnoreCase("obsidianaxe")) {
								ItemStack newOA = setMeta(new ItemStack(
										Material.DIAMOND_AXE),
										ChatColor.DARK_PURPLE + "Obsidian Axe",
										Arrays.asList(ChatColor.GRAY
												+ "Unbreakable Axe"));
								target.getInventory().addItem(newOA);
								target.sendMessage("");
								target.sendMessage("");
								target.sendMessage(PREFIX + "You received a "
										+ split[3]);

								if (split[2] != null) {
									int amount = Integer.parseInt(split[2]);
									newOA.setAmount(amount);
								}
								return true;
							}

							if (split[3].equalsIgnoreCase("explosivepick")) {
								ItemStack newEP = setMeta(new ItemStack(
										Material.DIAMOND_PICKAXE),
										ChatColor.RED + "Explosive Pickaxe",
										Arrays.asList(ChatColor.GRAY
												+ "Small Explosion"));
								target.getInventory().addItem(newEP);
								target.sendMessage("");
								target.sendMessage("");
								target.sendMessage(PREFIX + "You received a "
										+ split[3]);

								if (split[2] != null) {
									int amount = Integer.parseInt(split[2]);
									newEP.setAmount(amount);
								}
								return true;
							}
						} else {
							p.sendMessage("");
							p.sendMessage("");
							p.sendMessage(PREFIX
									+ "Specified player is not online!");
							return true;
						}
					}
					if (!split[3].equals("speedpick")
							|| !split[3].equals("explosivepick")
							|| !split[3].equals("obsidianaxe")
							|| !split[3].equals("obsidianpick")
							|| !split[3].equals("smelterspick")) {
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage(PREFIX
								+ "Item with that name doesn't exist!");
						return true;
					}
				} else {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(PREFIX
							+ "You don't have permission to use that command!");
					return true;
				}
			}
		}
		return false;
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
