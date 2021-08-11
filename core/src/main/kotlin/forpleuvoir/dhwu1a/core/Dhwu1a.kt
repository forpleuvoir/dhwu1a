package forpleuvoir.dhwu1a.core

import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig
import forpleuvoir.dhwu1a.core.config.LogConfig
import forpleuvoir.dhwu1a.core.event.base.EventBus
import forpleuvoir.dhwu1a.core.user.bot.Bot
import java.util.concurrent.ConcurrentLinkedDeque

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
class Dhwu1a private constructor() {
    companion object {
        val instance: Dhwu1a by lazy { Dhwu1a() }
    }

    private lateinit var config: Dhwu1aConfig
    lateinit var bot: Bot
        private set
    var isRunning = false
        private set
    val eventBus: EventBus = EventBus.instance
    private val onStart: MutableCollection<(Dhwu1a) -> Unit> = ConcurrentLinkedDeque()
    private val onClose: MutableCollection<(Dhwu1a) -> Unit> = ConcurrentLinkedDeque()
    private val onRestart: MutableCollection<(Dhwu1a) -> Unit> = ConcurrentLinkedDeque()

    fun initialize(config: Dhwu1aConfig) {
        Thread.currentThread().name = "dhwu1a"
        LogConfig.instance.copyOf(config.logConfig)
        this.config = config
        this.bot = Bot.instance
    }

    fun start() {
        bot.initialize(config)
        isRunning = true
        onStart()
    }

    private fun onStart() {
        onStart.forEach { it.invoke(this) }
    }

    fun reStart() {
        start()
        onRestart()
    }

    private fun onRestart() {
        onRestart.forEach { it.invoke(this) }
    }

    fun close() {
        bot.close()
        isRunning = false
        onClose()
    }

    private fun onClose() {
        onClose.forEach { it.invoke(this) }
    }

    fun addOnStartListener(listener: (Dhwu1a) -> Unit) {
        onStart.add(listener)
    }

    fun addOnRestartListener(listener: (Dhwu1a) -> Unit) {
        onRestart.add(listener)
    }

    fun addOnCloseListener(listener: (Dhwu1a) -> Unit) {
        onClose.add(listener)
    }


}