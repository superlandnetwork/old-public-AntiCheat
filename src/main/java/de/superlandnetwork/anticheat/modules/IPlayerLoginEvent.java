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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import de.superlandnetwork.anticheat.Main;

public class IPlayerLoginEvent implements Listener {
	
    @EventHandler
    public void onPlayerLogin(final PlayerLoginEvent event) {
    	if(!Main.getInstance().AntiSCHEMATICA)
    		return;
    	final Player player = event.getPlayer();
    	final byte[] payload = Schematica.getPayload(player);
    	if (payload != null) {
    		Schematica.sendCheatyPluginMessage(Main.getInstance(), player, Main.getInstance().SCHEMATICA, payload);
    		Schematica.sendCheatyPluginMessage(Main.getInstance(), player, Main.getInstance().SCHEMATICA, payload);
    	}
    	player.sendPluginMessage(Main.getInstance(), Main.getInstance().SCHEMATICA, payload);
    	player.sendPluginMessage(Main.getInstance(), Main.getInstance().SCHEMATICA, payload);
    }
}