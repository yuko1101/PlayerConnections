package com.github.yuko1101.playerconnections

import com.github.yuko1101.playerconnections.listeners.PlayerDisconnectListener
import com.github.yuko1101.playerconnections.listeners.ServerConnectedListener
import com.github.yuko1101.playerconnections.listeners.ServerKickListener
import com.github.yuko1101.playerconnections.listeners.ServerSwitchListener
import com.google.common.io.ByteStreams
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class PlayerConnections : Plugin() {

    companion object {
        lateinit var proxyServer: ProxyServer
        val messagePrefix = "§8[§6PlayerConnections§8] §r"
        val admins: List<UUID>
            get() {
                configuration["admins"]?.let {
                    return (it as List<*>).map { player -> UUID.fromString(player as String) }
                } ?: return emptyList()
            }

        lateinit var configuration: Configuration
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

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(
                loadResource(this, "config.yml")
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun loadResource(plugin: Plugin, resource: String): File {
        val folder = plugin.dataFolder
        if (!folder.exists()) folder.mkdir()
        val resourceFile = File(folder, resource)
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile()
                plugin.getResourceAsStream(resource).use { `in` ->
                    FileOutputStream(resourceFile).use { out ->
                        ByteStreams.copy(
                            `in`,
                            out
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resourceFile
    }
}