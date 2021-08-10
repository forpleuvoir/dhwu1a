package forpleuvoir.dhwu1a.core.event.bot

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.bot
 *
 * #class_name BotEventType
 *
 * #create_time 2021/7/3 20:28
 */
enum class BotEventType(val clazz: Class<out BotEvent?>) {
    BotOnlineEvent(forpleuvoir.dhwu1a.core.event.bot.BotOnlineEvent::class.java),
    BotOfflineEventActive(forpleuvoir.dhwu1a.core.event.bot.BotOfflineEventActive::class.java),
    BotOfflineEventForce(forpleuvoir.dhwu1a.core.event.bot.BotOfflineEventForce::class.java),
    BotOfflineEventDropped(forpleuvoir.dhwu1a.core.event.bot.BotOfflineEventDropped::class.java),
    BotReloginEvent(forpleuvoir.dhwu1a.core.event.bot.BotReloginEvent::class.java);

    companion object {
        fun hasKey(key: String): Boolean {
            var isHas = false
            for (value in values()) {
                isHas = value.name == key
                if (isHas) break
            }
            return isHas
        }
    }
}