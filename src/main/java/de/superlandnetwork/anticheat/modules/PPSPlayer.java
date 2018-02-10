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

import de.superlandnetwork.anticheat.Main;

public class PPSPlayer {

	private long startTime = System.currentTimeMillis();
	private int pps = -1;

	private void incrementReceived() {
		Long diff = System.currentTimeMillis() - startTime;
		if (diff >= 1000) {
			this.pps = 0;
			this.startTime = System.currentTimeMillis();
		}
		this.pps++;
	}

	public boolean handlePPS() {
		this.incrementReceived();
		return Main.maxPPS > 0 && this.pps > Main.maxPPS;
	}
}
