package forpleuvoir.dhwu1a.core.websocket.base

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.common.COMMAND
import forpleuvoir.dhwu1a.core.common.CONTENT
import forpleuvoir.dhwu1a.core.common.SUB_COMMAND
import forpleuvoir.dhwu1a.core.common.SYNC_ID
import forpleuvoir.dhwu1a.core.util.toJsonObject
import forpleuvoir.dhwu1a.core.websocket.command.Command
import java.util.concurrent.atomic.AtomicInteger

/**
 * 命令发送器
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.websocket.base
 *
 * #class_name CommandSender
 *
 * #create_time 2021/6/28 22:59
 */
class CommandSender private constructor() {
    val sendId: Int = syncId.get()
    private var command: String? = null
    private var subCommand: String? = null
    private var content: JsonObject? = null

    constructor(command: Command, content: JsonObject?) : this() {
        this.command = command.command
        this.content = content
    }

    constructor(command: Command) : this() {
        this.command = command.command
        content = null
    }

    constructor(command: Command, map: Map<*, *>) : this() {
        this.command = command.command
        content = map.toJsonObject()
    }

    constructor(command: String?, subCommand: String?, content: JsonObject?) : this() {
        this.command = command
        this.subCommand = subCommand
        this.content = content
    }

    fun toMessageJsonString(): String {
        val `object` = JsonObject()
        `object`.addProperty(SYNC_ID, sendId)
        `object`.addProperty(COMMAND, command)
        `object`.addProperty(SUB_COMMAND, subCommand)
        `object`.add(CONTENT, content)
        return `object`.toString()
    }

    companion object {
        private val syncId: AtomicInteger = AtomicInteger(0)
    }

    init {
        syncId.set(syncId.get() + 1)
        if (syncId.get() == 9999) {
            syncId.set(0)
        }
    }
}