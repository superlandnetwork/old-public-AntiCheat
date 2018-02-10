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

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.packet.PacketRegistry;
import com.google.common.collect.Sets;

import de.superlandnetwork.anticheat.itemfixer.ItemChecker;
import de.superlandnetwork.anticheat.itemfixer.NBTBukkitListener;
import de.superlandnetwork.anticheat.itemfixer.NBTListener;
import de.superlandnetwork.anticheat.itemfixer.TextureFix;
import de.superlandnetwork.anticheat.modules.AntiLabyMod;
import de.superlandnetwork.anticheat.modules.CommandBlock;
import de.superlandnetwork.anticheat.modules.IPlayerJoinEvent;
import de.superlandnetwork.anticheat.modules.IPlayerLoginEvent;
import de.superlandnetwork.anticheat.modules.IPluginMessageListener;
import de.superlandnetwork.anticheat.modules.PPSListener;

public class Main extends JavaPlugin {
	
	/* Options */
	public boolean AntiSkullExploit = false;
	public boolean AntiLabbyMod = true;
	public boolean Anti5zigMod = true;
	public boolean AntiBSM = true;
	public boolean AntiWDL = true;
	public boolean AntiSCHEMATICA = true;
	public boolean AntiDamageIndicator = true;
	public boolean AntiVoxelMap = true;
	public boolean AntiReisMinimap = true;
	public boolean AntiBPVP = true;
	public boolean BlockTab = false;
	public boolean BlockCommand = false;

	
	public boolean isLobby = false;
	
	public String ZIG = "5zig_Set";
	public String BSPRINT = "BSprint";
	public String BSM = "BSM";
	public String WDLINIT = "WDL|INIT";
	public String WDLCONTROL = "WDL|CONTROL";
	public String MCBRAND = "MC|Brand";
	public String WDLREQ = "WDL|REQUEST";
	public String SCHEMATICA = "schematica";
	public String FML = "FML";
	public String FMLHS = "FMLHS";
	private IPluginMessageListener pluginMessageListener = new IPluginMessageListener();
	
	public static Main instance;
	
	ProtocolManager protocolManager;
	
	//ItemFixer
	private ItemChecker checker;
	public static int maxPPS = 300;
	
	public void onEnable() {
		instance = this;
		PluginManager pm = Bukkit.getPluginManager();
		checker = new ItemChecker(this);
		protocolManager = ProtocolLibrary.getProtocolManager();
		if(AntiSkullExploit)
			protocolManager.addPacketListener(new NBTListener(this));
		protocolManager.addPacketListener(new PPSListener(this, getSupportedPackets()));
		if(pm.isPluginEnabled("Lobby"))
			isLobby = true;
		if(BlockTab) {
			protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }) {
				public void onPacketReceiving(PacketEvent event) {
					if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE) {
						try {
							PacketContainer packet = event.getPacket();
							String message = ((String)packet.getSpecificModifier(String.class).read(0)).toLowerCase();
							if ((message.startsWith("/")) && (!message.contains(" "))) {
								event.setCancelled(true);
							}
							if ((message.contains(":")) && (!message.contains(" "))) {
								event.setCancelled(true);
							}
						}
						catch (com.comphenix.protocol.reflect.FieldAccessException e) { e.printStackTrace(); }
					}
				}
			});
		}
		if(isLobby) {
			if(AntiLabbyMod)
				pm.registerEvents(new AntiLabyMod(), this);
			pm.registerEvents(new IPlayerJoinEvent(), this);
			pm.registerEvents(new IPlayerLoginEvent(), this);
			getServer().getMessenger().registerIncomingPluginChannel(this, ZIG, this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, BSM, this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, WDLINIT, this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, MCBRAND, this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, WDLREQ, this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, SCHEMATICA,  this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, FML, this.pluginMessageListener);
			getServer().getMessenger().registerIncomingPluginChannel(this, FMLHS, this.pluginMessageListener);
			getServer().getMessenger().registerOutgoingPluginChannel(this, MCBRAND);
			getServer().getMessenger().registerOutgoingPluginChannel(this, FML);
			getServer().getMessenger().registerOutgoingPluginChannel(this, FMLHS);
			getServer().getMessenger().registerOutgoingPluginChannel(this, ZIG);
			getServer().getMessenger().registerOutgoingPluginChannel(this, WDLCONTROL);
			getServer().getMessenger().registerOutgoingPluginChannel(this, BSM);
			getServer().getMessenger().registerOutgoingPluginChannel(this, SCHEMATICA);
		}
		if(BlockCommand)
			pm.registerEvents(new CommandBlock(), this);
		if(AntiSkullExploit) {
			pm.registerEvents(new NBTBukkitListener(this), this);
			pm.registerEvents(new TextureFix(this), this);
		}
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		protocolManager.removePacketListeners(this);
		NBTListener.cancel.clear();
		NBTListener.cancel = null;
		PPSListener.ppsPlayerByPlayer.clear();
		PPSListener.ppsPlayerByPlayer = null;
		checker = null;
		protocolManager = null;
		if(isLobby) {
			getServer().getMessenger().unregisterIncomingPluginChannel(this, ZIG, this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, BSM, this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, WDLINIT, this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, MCBRAND, this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, WDLREQ, this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, SCHEMATICA,  this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, FML, this.pluginMessageListener);
			getServer().getMessenger().unregisterIncomingPluginChannel(this, FMLHS, this.pluginMessageListener);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, MCBRAND);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, FML);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, FMLHS);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, ZIG);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, WDLCONTROL);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, BSM);
			getServer().getMessenger().unregisterOutgoingPluginChannel(this, SCHEMATICA);
		}
	}
	
	public boolean checkItem(ItemStack stack, Player p) {
		return checker.isHackedItem(stack, p);
	}
	
	private HashSet<PacketType> getSupportedPackets() {
		HashSet<PacketType> allSupportedPackets = Sets.newHashSet();
		for (PacketType type : PacketType.Play.Client.getInstance()) {
			if (PacketRegistry.isSupported(type)) allSupportedPackets.add(type);
		}
		return allSupportedPackets;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
}
