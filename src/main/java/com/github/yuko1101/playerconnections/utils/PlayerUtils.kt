package com.github.yuko1101.playerconnections.utils

import com.github.yuko1101.playerconnections.core.Version
import net.md_5.bungee.api.connection.ProxiedPlayer

object PlayerUtils {
    fun ProxiedPlayer.getVersion(): Version {
        return Version(this.pendingConnection.version)
    }
}