package forpleuvoir.dhwu1a.core.websocket.base

import com.google.gson.JsonObject
import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.common.SYNC_ID
import forpleuvoir.dhwu1a.core.event.base.EventBus
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.ifHasKey
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Thread.sleep
import java.net.URI
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.atomic.AtomicBoolean
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

    private var onWebSocketOpened: ((ServerHandshake) -> Unit)? = null

    protected val eventBus: EventBus = Dhwu1a.instance.eventBus

    var synchronizing: AtomicBoolean = AtomicBoolean(false)

    private val tasks = ConcurrentLinkedDeque<() -> Boolean>()

    protected fun addTask(task: () -> Boolean) {
        tasks.addLast(task)
    }

    protected fun executeTask() {
        runBlocking {
            launch {
                if (!synchronizing.get() && !tasks.isEmpty())
                    tasks.removeIf {
                        it.invoke()
                    }
            }
        }
    }

    override fun onOpen(handshakeData: ServerHandshake) {
        Thread.currentThread().name = "$name wsc"
        log.info(
            "WebSocketClient 初始化[code:{},message:{}]", handshakeData.httpStatus,
            handshakeData.httpStatusMessage
        )
        onWebSocketOpened?.invoke(handshakeData)
    }

    fun setOnOpenCallback(callback: ((ServerHandshake) -> Unit)?) {
        onWebSocketOpened = callback
    }

    abstract fun onMessageAsync(jsonObject: JsonObject)

    override fun onMessage(message: String?) {
        runBlocking {
            launch {
                message?.let {
                    message.ifHasKey(SYNC_ID)?.let {
                        onMessageAsync(it)
                    }

                }
            }
        }
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        log.info("WebSocketClient {} 关闭", name)
        if (Dhwu1a.instance.isRunning) {
            Thread {
                sleep(5000)
                reconnect()
            }
        } else {
            runBlocking {
                launch {
                    delay(5000)
                    exitProcess(0)
                }
            }
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