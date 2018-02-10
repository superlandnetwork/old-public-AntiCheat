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
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.superlandnetwork.anticheat.Main;

public class Schematica {
    
    public static byte[] getPayload(final Player player) {
    	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    	final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    	try {
    		dataOutputStream.writeByte(0);
    		dataOutputStream.writeBoolean(false);
    		dataOutputStream.writeBoolean(false);
    		dataOutputStream.writeBoolean(false);

    		return byteArrayOutputStream.toByteArray();
    	} catch (final IOException ioe) {
    		ioe.printStackTrace();
    	}
    	return null;
    }
    public static void sendCheatyPluginMessage(Plugin plugin, final Player player, final String channel, final byte[] payload) {
    	plugin = Main.getInstance();
    	try {
    		final Class<? extends Player> playerClass = player.getClass();
    		if (playerClass.getSimpleName().equals("CraftPlayer")) {
    			final Method addChannel = playerClass.getDeclaredMethod("addChannel", String.class);
    			final Method removeChannel = playerClass.getDeclaredMethod("removeChannel", String.class);

    			addChannel.invoke(player, channel);
    			player.sendPluginMessage(plugin, channel, payload);
    			removeChannel.invoke(player, channel);
    		}
    	} catch (final NoSuchMethodException nsme) {
    		nsme.printStackTrace();
    	} catch (final InvocationTargetException ite) {
    		ite.printStackTrace();
    	} catch (final IllegalAccessException iae) {
    		iae.printStackTrace();
    	} catch (final Exception e) {
    		e.printStackTrace();
    	}
    }
}
