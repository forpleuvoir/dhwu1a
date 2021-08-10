package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.NAME
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name Poke
 *
 * #create_time 2021/6/30 0:28
 */
class Poke(name: String?) : MessageItem(MessageItemType.Poke) {
    /**
     * 戳一戳的类型
     */
    @SerializedName(NAME)
    val name: PokeType
    override fun toPlainText(): String {
        return name.type
    }

    /**
     * 戳一戳的类型
     */
    enum class PokeType(val type: String) {
        Poke("戳一戳"), ShowLove("比心"), Like("点赞"), Heartbroken("心碎"), SixSixSix("666"), FangDaZhao("放大招");

    }

    init {
        this.name = PokeType.valueOf(name!!)
    }
}