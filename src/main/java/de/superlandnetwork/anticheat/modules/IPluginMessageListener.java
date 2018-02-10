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

import java.io.UnsupportedEncodingException;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.superlandnetwork.anticheat.Main;
import de.superlandnetwork.anticheat.Notify;

public class IPluginMessageListener implements PluginMessageListener {
	
	public void onPluginMessageReceived(String channel, Player player, byte[] value) {		  
    	if (channel.equalsIgnoreCase(Main.getInstance().BSM)) {
    		ByteArrayDataOutput out1 = ByteStreams.newDataOutput();
    		out1.writeByte(1);
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().BSM, out1.toByteArray());
    	}
    	
    	if ((channel.equalsIgnoreCase(Main.getInstance().ZIG)) || (channel.contains("5zig"))) {
    		ByteArrayDataOutput out1 = ByteStreams.newDataOutput();
    		out1.writeByte(0x01);
    		ByteArrayDataOutput out2 = ByteStreams.newDataOutput();
    		out2.writeByte(0x02);
    		ByteArrayDataOutput out4 = ByteStreams.newDataOutput();
    		out4.writeByte(0x04);
    		ByteArrayDataOutput out8 = ByteStreams.newDataOutput();
    		out8.writeByte(0x08);
    		ByteArrayDataOutput out10 = ByteStreams.newDataOutput();
    		out10.writeByte(0x010);
			 
    		ByteArrayDataOutput all = ByteStreams.newDataOutput();
    		all.writeByte(0x01 | 0x02 | 0x04 | 0x08 | 0x010);
				  	
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().ZIG, out1.toByteArray());
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().ZIG, out2.toByteArray());
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().ZIG, out4.toByteArray());
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().ZIG, out8.toByteArray());
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().ZIG, out10.toByteArray());
    		player.sendPluginMessage(Main.getInstance(), Main.getInstance().ZIG, all.toByteArray());
    	}
    	
    	if ((channel.equalsIgnoreCase(Main.getInstance().WDLINIT)) || (channel.equalsIgnoreCase(Main.getInstance().WDLCONTROL)) ||
    		(channel.equalsIgnoreCase(Main.getInstance().WDLREQ)) || (channel.contains("WDL"))) {
    		new Notify().NotifyWDL(player);
    	}
    	
    	if (channel.equalsIgnoreCase(Main.getInstance().MCBRAND)) {
    		String brand;
    		try {
    			brand = new String(value, "UTF-8");
    		} catch (UnsupportedEncodingException e) {
    			throw new Error(e);
    		}
    		
    		if (brand.equalsIgnoreCase("worlddownloader-vanilla")) {
        		new Notify().NotifyWDL(player);
    		}
    	}
    }
}
