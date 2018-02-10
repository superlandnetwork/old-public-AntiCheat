//    _            _     _    ___   _                   _   
//   /_\    _ _   | |_  (_)  / __| | |_    ___   __ _  | |_ 
//  / _ \  | ' \  |  _| | | | (__  | ' \  / -_) / _` | |  _|
// /_/ \_\ |_||_|  \__| |_|  \___| |_||_| \___| \__,_|  \__|
//
// Copyright (C) Filli-IT (Einzelunternehmen) & Ursin Filli - All Rights Reserverd
// Unauthorized copying of the this file, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Ursin Filli <ursin.filli@Filli-IT.ch>

package de.superlandnetwork.anticheat;

import org.bukkit.entity.Player;

import de.superlandnetwork.API.PlayerAPI.PlayerAPI;

public class Notify {

	public void NotifyWDL(Player p) {
		//15 Tage Ban
		new PlayerAPI(p.getUniqueId()).Ban("WDL (WorldDownLoader Mod)", 1296000, "AntiCheat | AntiWDL");
	}

}
