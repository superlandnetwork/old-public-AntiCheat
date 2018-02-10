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

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.superlandnetwork.anticheat.Main;
import de.superlandnetwork.anticheat.modules.PPSListener;

public class NBTBukkitListener implements Listener {
	
	private final Main plugin;
	
	public NBTBukkitListener(Main Main) {
		this.plugin = Main;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onInvClick(InventoryClickEvent event) {
		if (event.getWhoClicked().getType() != EntityType.PLAYER) return;
		final Player p = (Player) event.getWhoClicked();
		if (event.getCurrentItem() == null) return;
		if (plugin.checkItem(event.getCurrentItem(), p)) {
			event.setCancelled(true);
			p.updateInventory();
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onDrop(PlayerDropItemEvent event) {
		final Player p = event.getPlayer();
		if (event.getItemDrop() == null) return;
		if (plugin.checkItem(event.getItemDrop().getItemStack(), p)) {
			event.setCancelled(true);
			p.updateInventory();
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		NBTListener.cancel.remove(event.getPlayer());
		PPSListener.ppsPlayerByPlayer.remove(event.getPlayer());
	}
}

