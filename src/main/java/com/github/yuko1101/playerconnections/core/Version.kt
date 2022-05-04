package com.github.yuko1101.playerconnections.core

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.net.URL


class Version(val id: Int) {
    val name: String
        get() = getVersionName(id)

    // https://wiki.vg/Protocol_version_numbers
    private fun getVersionData(): JsonObject {
        val data: String? = this.javaClass.classLoader.getResource("versions.json")?.readText()
        return JsonParser().parse(data) as JsonObject
    }
    private fun getVersionName(id: Int): String {
        val versions = getVersionData()
        val version = versions.entrySet().first { (it.value as JsonObject)["protocol_id"]?.asInt == id || (it.key == id.toString() && !(it.value as JsonObject).has("protocol_id")) }
        return (version.value as JsonObject)["name"].asString
    }
}