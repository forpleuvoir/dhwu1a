package forpleuvoir.dhwu1a.core.websocket.base

import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.event.base.EventBus
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Thread.sleep
import java.net.URI
import kotlin.system.exitProcess

/**
 * websocket客户端
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.websocket.base
 *
 * #class_name Dhwu1aWebSocketClient
 *
 * #create_time 2021/6/28 20:48
 */
abstract class Dhwu1aWebSocketClient(serverUri: String, protected val bot: Bot, protected val name: String) :
    WebSocketClient(
        URI(serverUri)
    ) {

    @Transient
    private var onWebSocketOpened: ((ServerHandshake) -> Unit)? = null

    @JvmField
    protected val eventBus: EventBus = Dhwu1a.instance!!.eventBus!!

    override fun onOpen(handshakeData: ServerHandshake) {
        Thread.currentThread().name = name
        log.info(
            "WebSocketClient 初始化[code:{},message:{}]", handshakeData.httpStatus,
            handshakeData.httpStatusMessage
        )
        onWebSocketOpened?.invoke(handshakeData)
    }

    fun setOnOpenCallback(callback: ((ServerHandshake) -> Unit)?) {
        onWebSocketOpened = callback
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        log.info("WebSocketClient {} 关闭", name)
        if (Dhwu1a.instance!!.isRunning) {
            Thread { reconnect() }.start()
        } else {
            Thread {
                sleep(5000)
                exitProcess(0)
            }.start()
        }

    }

    override fun onError(ex: Exception) {
        log.error(ex.message, ex)
    }

    companion object {
        @Transient
        private val log = Dhwu1aLog(Dhwu1aWebSocketClient::class.java)
    }

}