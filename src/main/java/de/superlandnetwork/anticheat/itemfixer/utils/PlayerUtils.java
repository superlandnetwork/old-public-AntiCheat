//    _            _     _    ___   _                   _   
//   /_\    _ _   | |_  (_)  / __| | |_    ___   __ _  | |_ 
//  / _ \  | ' \  |  _| | | | (__  | ' \  / -_) / _` | |  _|
// /_/ \_\ |_||_|  \__| |_|  \___| |_||_| \___| \__,_|  \__|
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.anticheat.itemfixer.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.events.PacketEvent;

import net.sf.cglib.proxy.Factory;

public class PlayerUtils {

	public static Player getPlayerFromEvent(PacketEvent event) {
		Player eventPlayer = event.getPlayer();

		if (eventPlayer == null || !eventPlayer.isOnline()) return null;

		String playerName = eventPlayer.getName();

		if (playerName == null || playerName.isEmpty()) return null;

		Player bukkitPlayer = Bukkit.getPlayerExact(playerName);

		if (bukkitPlayer == null || !bukkitPlayer.isOnline()) return null;

		if ("InjectorContainer".equals(bukkitPlayer.getClass().getSimpleName()) || bukkitPlayer instanceof Factory) return null;

		return bukkitPlayer;
	}
}
