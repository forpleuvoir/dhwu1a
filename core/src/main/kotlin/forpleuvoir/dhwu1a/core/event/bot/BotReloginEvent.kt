package forpleuvoir.dhwu1a.core.event.bot

/**
 * Bot主动重新登录
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.bot
 *
 * #class_name BotReloginEvent
 *
 * #create_time 2021/7/3 20:48
 */
class BotReloginEvent : BotEvent(BotEventType.BotReloginEvent){

    override fun callback() {
        bot.sync()
    }

}