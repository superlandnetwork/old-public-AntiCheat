//    _            _     _    ___   _                   _   
//   /_\    _ _   | |_  (_)  / __| | |_    ___   __ _  | |_ 
//  / _ \  | ' \  |  _| | | | (__  | ' \  / -_) / _` | |  _|
// /_/ \_\ |_||_|  \__| |_|  \___| |_||_| \___| \__,_|  \__|
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.anticheat.itemfixer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import de.superlandnetwork.anticheat.Main;

@SuppressWarnings("deprecation")
public class TextureFix implements Listener {

	private HashMap<Material,Integer> limit = new HashMap<Material, Integer>();
	private HashSet<Material> ignore = new HashSet<Material>();

	public TextureFix(Main main) {
		//Material, MaxSubId
		limit.put(Material.STONE, 6);
		limit.put(Material.DIRT, 2);
		limit.put(Material.WOOD, 5);
		limit.put(Material.SAPLING, 5);
		limit.put(Material.SAND, 1);
		limit.put(Material.LOG, 3);
		limit.put(Material.LEAVES, 3);
		limit.put(Material.SPONGE, 1);
		limit.put(Material.SANDSTONE, 2);
		limit.put(Material.LONG_GRASS, 2);
		limit.put(Material.WOOL, 15);
		limit.put(Material.RED_ROSE, 8);
		limit.put(Material.DOUBLE_STEP, 7);
		limit.put(Material.STEP, 7);
		limit.put(Material.STAINED_GLASS, 15);
		limit.put(Material.MONSTER_EGGS, 5);
		limit.put(Material.SMOOTH_BRICK, 3);
		limit.put(Material.WOOD_DOUBLE_STEP, 5);
		limit.put(Material.WOOD_STEP, 5);
		limit.put(Material.COBBLE_WALL, 1);
		limit.put(Material.QUARTZ_BLOCK, 2);
		limit.put(Material.STAINED_CLAY, 15);
		limit.put(Material.STAINED_GLASS, 15);
		limit.put(Material.STAINED_GLASS_PANE, 15);
		limit.put(Material.LEAVES_2, 1);
		limit.put(Material.LOG_2, 1);
		limit.put(Material.PRISMARINE, 2);
		limit.put(Material.CARPET, 15);
		limit.put(Material.DOUBLE_PLANT, 5);
		limit.put(Material.RED_SANDSTONE, 2);
		limit.put(Material.COAL, 1);
		limit.put(Material.RAW_FISH, 3);
		limit.put(Material.COOKED_FISH, 1);
		limit.put(Material.INK_SACK, 15);
		limit.put(Material.SKULL_ITEM, 5);
		limit.put(Material.GOLDEN_APPLE, 1);
		limit.put(Material.BANNER, 15);
		limit.put(Material.ANVIL, 2);
		ignore.addAll(Arrays.asList(Material.MAP, Material.EMPTY_MAP, Material.CARROT_STICK, Material.BOW, Material.FISHING_ROD, Material.FLINT_AND_STEEL, Material.SHEARS));
		ignore.add(Material.MONSTER_EGG);
		ignore.add(Material.POTION);
		limit.put(Material.SKULL_ITEM, 4);

		ignore.addAll(Arrays.asList(Material.WOOD_SPADE, Material.WOOD_PICKAXE, Material.WOOD_AXE, Material.WOOD_SWORD, Material.WOOD_HOE));
		ignore.addAll(Arrays.asList(Material.GOLD_SPADE, Material.GOLD_PICKAXE, Material.GOLD_AXE, Material.GOLD_SWORD, Material.GOLD_HOE));
		ignore.addAll(Arrays.asList(Material.STONE_SPADE, Material.STONE_PICKAXE, Material.STONE_AXE, Material.STONE_SWORD, Material.STONE_HOE));
		ignore.addAll(Arrays.asList(Material.IRON_SPADE, Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_SWORD, Material.IRON_HOE));
		ignore.addAll(Arrays.asList(Material.DIAMOND_SPADE, Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_SWORD, Material.DIAMOND_HOE));
		ignore.addAll(Arrays.asList(Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS));
		ignore.addAll(Arrays.asList(Material.IRON_BOOTS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS));
		ignore.addAll(Arrays.asList(Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE, Material.GOLD_HELMET, Material.GOLD_LEGGINGS));
		ignore.addAll(Arrays.asList(Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS));
		ignore.addAll(Arrays.asList(Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS));
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onHold(PlayerItemHeldEvent e) {
		ItemStack it = e.getPlayer().getInventory().getItem(e.getNewSlot());
		if (isInvalide(it)) {
			e.setCancelled(true);
			e.getPlayer().getInventory().remove(it);
			e.getPlayer().updateInventory();
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onInteract(PlayerInteractEvent e) {
		ItemStack it = e.getItem();
		if (isInvalide(it)) {
			e.setCancelled(true);
			e.getPlayer().getInventory().remove(it);
			e.getPlayer().updateInventory();
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onClick(InventoryClickEvent e) {
		ItemStack it = e.getCurrentItem();
		if (e.getWhoClicked().getType() == EntityType.PLAYER && isInvalide(it)) {
			e.setCancelled(true);
			e.getWhoClicked().getInventory().remove(it);
			((Player)e.getWhoClicked()).updateInventory();
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onPickup(PlayerPickupItemEvent e) { //Deprecated
		ItemStack it = e.getItem().getItemStack();
		if (isInvalide(it)) {
			e.setCancelled(true);
			e.getItem().remove();
		}
	}

	private boolean isInvalide(ItemStack it) {
		if (it != null && it.getType()!=Material.AIR && it.getDurability() != 0) {
			if (limit.containsKey(it.getType())) {
				return (it.getDurability() < 0 || (it.getDurability() > limit.get(it.getType())));
			}
			return !ignore.contains(it.getType());
		}
		return false;
	}
}
