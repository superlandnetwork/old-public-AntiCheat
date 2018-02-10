//    _            _     _    ___   _                   _   
//   /_\    _ _   | |_  (_)  / __| | |_    ___   __ _  | |_ 
//  / _ \  | ' \  |  _| | | | (__  | ' \  / -_) / _` | |  _|
// /_/ \_\ |_||_|  \__| |_|  \___| |_||_| \___| \__,_|  \__|
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.anticheat.modules;

import java.util.HashSet;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import de.superlandnetwork.anticheat.Main;
import de.superlandnetwork.anticheat.itemfixer.utils.PlayerUtils;

public class PPSListener extends PacketAdapter {

	public static WeakHashMap<Player, PPSPlayer> ppsPlayerByPlayer;

	public PPSListener(Main plugin, HashSet<PacketType> types) {
		super(plugin, ListenerPriority.NORMAL, types);
		ppsPlayerByPlayer = new WeakHashMap<Player, PPSPlayer>();
	}
 
	@Override
	public void onPacketReceiving(PacketEvent event) {
		if (event.isCancelled()) return;
		Player p = PlayerUtils.getPlayerFromEvent(event);
		if (p == null) return;
		if (ppsPlayerByPlayer.containsKey(p)) {
			if (ppsPlayerByPlayer.get(p).handlePPS()) {
				event.setCancelled(true);
				Bukkit.getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						p.kickPlayer("§7[§aAntiCheat§7] §cYou are sending too many packets!");
					}
				});
			}
		} else {
			ppsPlayerByPlayer.put(p, new PPSPlayer());
		}
	}
}

