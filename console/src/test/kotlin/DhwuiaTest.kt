import forpleuvoir.dhwu1a.core.Dhwu1a.Companion.instance
import forpleuvoir.dhwu1a.core.config.Dhwu1aConfig
import forpleuvoir.dhwu1a.core.config.LogConfig
import forpleuvoir.dhwu1a.core.event.message.MessageEvent

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package PACKAGE_NAME
 *
 * #class_name DhwuiaTest
 *
 * #create_time 2021-08-07 13:23
 */
fun main(args: Array<String>) {
    val dhwuia = instance
    dhwuia.initialize(Dhwu1aConfig(2481735118L, "bot.forpleuvoir.com", 2333, "FORPLEUVOIR", LogConfig()))
    dhwuia.start()
    val eventBus = dhwuia.eventBus
    eventBus.subscribe { messageEvent: MessageEvent<*> ->
        if (messageEvent.message.toPlainText() == "close") {
            dhwuia.close()
        }
    }
}