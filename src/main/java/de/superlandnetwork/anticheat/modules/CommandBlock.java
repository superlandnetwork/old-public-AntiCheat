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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandBlock implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		
		String command = event.getMessage();
		
		command.replace("/", "");
		if (command.split(" ")[0].contains(":")) {
			event.setCancelled(true);
		}
		
	}
}
