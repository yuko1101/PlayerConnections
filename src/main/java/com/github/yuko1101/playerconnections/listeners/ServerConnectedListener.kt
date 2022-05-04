package com.github.yuko1101.playerconnections.listeners

import com.github.yuko1101.playerconnections.PlayerConnections
import com.github.yuko1101.playerconnections.utils.PlayerUtils.getVersion
import net.md_5.bungee.api.event.ServerConnectedEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class ServerConnectedListener : Listener {
    @EventHandler
    fun onServerConnected(event: ServerConnectedEvent) {
        val player = event.player
        val from = event.player.server
        val to = event.server
        if (from?.info != null) return

        val country = player.locale?.country ?: "unknown"

        val message = "${PlayerConnections.messagePrefix}§e${player.name}§7[${player.getVersion().name}]§f: §eConnect -> ${to.info.name}"
        PlayerConnections.broadcastToAdmins(message)

    }
}