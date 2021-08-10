package forpleuvoir.dhwu1a.core

import forpleuvoir.dhwu1a.core.event.base.EventBus.Companion.getInstance
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig
import forpleuvoir.dhwu1a.core.config.LogConfig
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.event.base.EventBus
import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Consumer

/**
 * 程序入口
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core
 *
 * #class_name Dhwu1a
 *
 * #create_time 2021/6/28 20:28
 */
class Dhwu1a private constructor(config: Dhwu1aConfig) {
    @JvmField
    val logConfig: LogConfig
    var bot: Bot? = null
    var isRunning = false
        private set
    private val config: Dhwu1aConfig
    var eventBus: EventBus? = null
        private set
    private val onStart: MutableList<Consumer<Dhwu1a>> = CopyOnWriteArrayList()
    private val onClose: MutableList<Consumer<Dhwu1a>> = CopyOnWriteArrayList()
    private val onRestart: MutableList<Consumer<Dhwu1a>> = CopyOnWriteArrayList()

    fun start() {
        eventBus = getInstance()
        Bot.initialize(config)
        bot = Bot.instance
        isRunning = true
        onStart()
    }

    private fun onStart() {
        onStart.forEach(Consumer { onStartConsumer: Consumer<Dhwu1a> -> onStartConsumer.accept(this) })
    }

    fun reStart() {
        start()
        onRestart()
    }

    private fun onRestart() {
        onRestart.forEach(Consumer { onRestartConsumer: Consumer<Dhwu1a> -> onRestartConsumer.accept(this) })
    }

    fun close() {
        bot!!.close()
        bot = null
        isRunning = false
        onClose()
    }

    private fun onClose() {
        onClose.forEach(Consumer { onCloseConsumer: Consumer<Dhwu1a> -> onCloseConsumer.accept(this) })
    }

    fun addOnStartListener(consumer: Consumer<Dhwu1a>) {
        onStart.add(consumer)
    }

    fun addOnRestartListener(consumer: Consumer<Dhwu1a>) {
        onRestart.add(consumer)
    }

    fun addOnCloseListener(consumer: Consumer<Dhwu1a>) {
        onClose.add(consumer)
    }

    companion object {
        var instance: Dhwu1a? = null
            private set

        @JvmStatic
        fun initialize(config: Dhwu1aConfig) {
            if (instance == null) {
                instance = Dhwu1a(config)
            }
        }
    }

    init {
        Thread.currentThread().name = "dhwu1a"
        logConfig = config.logConfig
        this.config = config
    }
}