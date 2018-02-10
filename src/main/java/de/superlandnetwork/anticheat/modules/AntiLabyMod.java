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

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;

public class AntiLabyMod implements Listener {

	public static void disableMod(Player p, List<LabyMod> mods) {
		try{
			HashMap<String, Boolean> dList = new HashMap<String, Boolean>();
			for(LabyMod mod : mods) {
				dList.put(mod.toString(), false);
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(dList);
			ByteBuf bb = Unpooled.copiedBuffer(out.toByteArray());
			PacketDataSerializer serializer = new PacketDataSerializer(bb);
			PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("LABYMOD", serializer);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public static enum LabyMod {
		FOOD, 
		GUI,
		NICK,
		BLOCKBUILD,
		CHAT,
		EXTRAS,
		ANIMATIONS,
		POTIONS,
		ARMOR,
		DAMAGEINDICATOR,
		MINIMAP_RADAR,
		SATURATION_BAR, 
		GUI_ALL, 
		GUI_POTION_EFFECTS, 
		GUI_ARMOR_HUD,
		GUI_ITEM_HUD,
		TAGS,
		IMPROVED_LAVA,
		CROSSHAIR_SYNC,
		REFILL_FIX;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		List<LabyMod> mods = new ArrayList<>();
		mods.add(LabyMod.MINIMAP_RADAR);
		mods.add(LabyMod.DAMAGEINDICATOR);
		AntiLabyMod.disableMod(e.getPlayer(), mods);
		
	}
	
	
}
