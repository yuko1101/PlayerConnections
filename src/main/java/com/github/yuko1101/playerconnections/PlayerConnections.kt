package com.github.yuko1101.playerconnections

import com.github.yuko1101.playerconnections.listeners.PlayerDisconnectListener
import com.github.yuko1101.playerconnections.listeners.ServerConnectedListener
import com.github.yuko1101.playerconnections.listeners.ServerKickListener
import com.github.yuko1101.playerconnections.listeners.ServerSwitchListener
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.plugin.Plugin
import java.util.*

class PlayerConnections : Plugin() {

    companion object {
        lateinit var proxyServer: ProxyServer
        val messagePrefix = "§8[§6PlayerConnections§8] §r"
        val admins = listOf(
            UUID.fromString("1cc24828-04a3-4ed7-aa7d-cf84c8d3ae88"),
//            UUID.fromString("c77f7fc1-7a66-453c-b18b-5d2967f5fd06"),
//            UUID.fromString("2236d750-cac8-4e2b-9658-65c0754cc1dc")
        )
        fun broadcastToAdmins(message: TextComponent) {
            for (admin in admins) {
                proxyServer.getPlayer(admin)?.sendMessage(message)
            }
        }
        fun broadcastToAdmins(message: String) {
            for (admin in admins) {
                proxyServer.getPlayer(admin)?.sendMessage(TextComponent(message))
            }
        }
    }

    override fun onEnable() {
        // Plugin startup logic
        proxyServer = proxy
        proxy.pluginManager.registerListener(this, ServerSwitchListener())
        proxy.pluginManager.registerListener(this, ServerKickListener())
        proxy.pluginManager.registerListener(this, PlayerDisconnectListener())
        proxy.pluginManager.registerListener(this, ServerConnectedListener())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}