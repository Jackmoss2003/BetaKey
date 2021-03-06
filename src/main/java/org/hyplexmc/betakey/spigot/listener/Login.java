/*
 *  Developed by Luuuuuis.
 *  Last modified 09.05.20, 20:35.
 *  Copyright (c) 2021.
 */

package org.hyplexmc.betakey.spigot.listener;

import org.hyplexmc.betakey.database.querys.BetaPlayer;
import org.hyplexmc.betakey.misc.Config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Login implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        BetaPlayer betaPlayer = new BetaPlayer(player.getUniqueId());

        if (!betaPlayer.isValid()) {
            e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, ChatColor.translateAlternateColorCodes('&', Config.getInstance().getKickMessage()));
            System.out.println("BetaKey DEBUG >> Not valid!");
        } else
            System.out.println("BetaKey DEBUG >> Valid!");

    }

}
