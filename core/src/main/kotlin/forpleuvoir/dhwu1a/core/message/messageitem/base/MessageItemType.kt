package forpleuvoir.dhwu1a.core.message.messageitem.base


/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem.base
 *
 * #class_name MessageItemType
 *
 * #create_time 2021/6/29 23:03
 */
enum class MessageItemType(val clazz: Class<out MessageItem?>) {
    Source(forpleuvoir.dhwu1a.core.message.messageitem.Source::class.java),
    Quote(forpleuvoir.dhwu1a.core.message.messageitem.Quote::class.java),
    At(forpleuvoir.dhwu1a.core.message.messageitem.At::class.java),
    AtAll(forpleuvoir.dhwu1a.core.message.messageitem.AtAll::class.java),
    Face(forpleuvoir.dhwu1a.core.message.messageitem.Face::class.java),
    Plain(forpleuvoir.dhwu1a.core.message.messageitem.Plain::class.java),
    Image(forpleuvoir.dhwu1a.core.message.messageitem.Image::class.java),
    FlashImage(forpleuvoir.dhwu1a.core.message.messageitem.FlashImage::class.java),
    Voice(forpleuvoir.dhwu1a.core.message.messageitem.Voice::class.java),
    Xml(forpleuvoir.dhwu1a.core.message.messageitem.Xml::class.java),
    Json(forpleuvoir.dhwu1a.core.message.messageitem.Json::class.java),
    App(forpleuvoir.dhwu1a.core.message.messageitem.App::class.java),
    Poke(forpleuvoir.dhwu1a.core.message.messageitem.Poke::class.java),
    Forward(forpleuvoir.dhwu1a.core.message.messageitem.Forward::class.java),
    File(forpleuvoir.dhwu1a.core.message.messageitem.File::class.java),
    MusicShare(forpleuvoir.dhwu1a.core.message.messageitem.MusicShare::class.java),
    Dice(forpleuvoir.dhwu1a.core.message.messageitem.Dice::class.java);
}