package com.github.yuko1101.playerconnections.listeners

import com.github.yuko1101.playerconnections.PlayerConnections
import com.github.yuko1101.playerconnections.utils.PlayerUtils.getVersion
import net.md_5.bungee.api.event.ServerKickEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class ServerKickListener : Listener {
    @EventHandler
    fun onServerKick(event: ServerKickEvent) {
        val player = event.player
        val reason = event.kickReason
        val server = event.kickedFrom

        val message = "${PlayerConnections.messagePrefix}§e${player.name}§7[${player.getVersion().name}, ${player.locale.country}]§f: §e${server.name} -> Disconnect §7(kicked from ${server.name}: ${reason}§r§7)"

        PlayerConnections.broadcastToAdmins(message)
    }
}