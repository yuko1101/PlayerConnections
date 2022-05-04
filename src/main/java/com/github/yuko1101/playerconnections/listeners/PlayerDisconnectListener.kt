package com.github.yuko1101.playerconnections.listeners

import com.github.yuko1101.playerconnections.PlayerConnections
import com.github.yuko1101.playerconnections.utils.PlayerUtils.getVersion
import net.md_5.bungee.api.event.PlayerDisconnectEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class PlayerDisconnectListener : Listener {
    @EventHandler
    fun onPlayerDisconnect(event: PlayerDisconnectEvent) {
        val player = event.player
        val server = player.server
        if (server?.info != null) return

        val country = player.locale?.country ?: "unknown"

        val message = "${PlayerConnections.messagePrefix}§e${player.name}§7[${player.getVersion().name}, ${country}]§f: §eConnect -> Disconnect"
        PlayerConnections.broadcastToAdmins(message)


    }
}