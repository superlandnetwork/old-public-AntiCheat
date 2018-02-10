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

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.superlandnetwork.anticheat.Main;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class IPlayerJoinEvent implements Listener {
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	
    	if(Main.getInstance().AntiBPVP)
    		sendBetterPvP(p);
    	if(Main.getInstance().AntiSCHEMATICA)
    		sendSchematica(p);
    	if(Main.getInstance().AntiReisMinimap)
    		sendReiMiniMap(p);
    	if(Main.getInstance().AntiDamageIndicator)
    		sendDamageIndicators(p);
    	if(Main.getInstance().AntiVoxelMap)
    		sendVoxelMap(p);
    	if(Main.getInstance().AntiBSM)
    		sendSmartMove(p);
    }
	
	public void sendBetterPvP(Player p) {
		try {
			String json = "{\"text\":\"\",\"extra\":[{\"text\":\"§c §r§5 §r§1 §r§f §r§0\"}]}";
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(json);
			PacketPlayOutChat chat = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendSchematica(Player p) {
		try {
			String json = "{\"text\":\"\",\"extra\":[{\"text\":\"§0§2§0§0§e§f\"},{\"text\":\"§0§2§1§0§e§f\"},{\"text\":\"§0§2§1§1§e§f\"}]}";
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(json);
			PacketPlayOutChat chat = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendReiMiniMap(Player p) {
		try {
			String json = "{\"text\":\"\",\"extra\":[{\"text\":\"§0§0§1§2§3§5§e§f\"},{\"text\":\"§0§0§2§3§4§5§6§7§e§f\"},"
					+ "{\"text\":\"§A§n§t§i§M§i§n§i§m§a§p\"}]}";
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(json);
			PacketPlayOutChat chat = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendDamageIndicators(Player p) {
		try {
			String json = "{\"text\":\"\",\"extra\":[{\"text\":\"§0§0§c§d§e§f\"}]}";
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(json);
			PacketPlayOutChat chat = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendVoxelMap(Player p) {
		try {
			String json = "{\"text\":\"\",\"extra\":[{\"text\":\"§3 §6 §3 §6 §3 §6 §d\"},{\"text\":\"§3 §6 §3 §6 §3 §6 §e\"}]}";
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(json);
			PacketPlayOutChat chat = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendSmartMove(Player p) {
		try {
			String json = "{\"text\":\"\",\"extra\":[{\"text\":\"§0§1§0§1§2§f§f\"},{\"text\":\"§0§1§3§4§f§f\"},"
					+ "{\"text\":\"§0§1§5§f§f\"},{\"text\":\"§0§1§6§f§f\"},{\"text\":\"§0§1§8§9§a§b§f§f\"}]}";
			IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(json);
			PacketPlayOutChat chat = new PacketPlayOutChat(icbc);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
