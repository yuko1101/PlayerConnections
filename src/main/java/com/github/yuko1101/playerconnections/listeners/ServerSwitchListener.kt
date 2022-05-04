package com.github.yuko1101.playerconnections.listeners

import com.github.yuko1101.playerconnections.PlayerConnections
import com.github.yuko1101.playerconnections.utils.PlayerUtils.getVersion
import net.md_5.bungee.api.event.ServerSwitchEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class ServerSwitchListener : Listener {
    @EventHandler
    fun onServerSwitch(event: ServerSwitchEvent) {
        val player = event.player
        val fromServer = event.from
        val toServer = player.server.info
        if (fromServer != null && toServer != null) {
            val fromServerName = fromServer.name
            val toServerName = toServer.name
            val message = PlayerConnections.messagePrefix + "§e${player.name}§7[${player.getVersion().name}, ${player.locale.country}]§f: §e${fromServerName} -> $toServerName"
            PlayerConnections.broadcastToAdmins(message)
        }
    }
}