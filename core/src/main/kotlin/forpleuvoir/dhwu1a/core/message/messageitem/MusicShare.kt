package forpleuvoir.dhwu1a.core.message.messageitem

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.messageitem
 *
 * #class_name MusicShare
 *
 * #create_time 2021/6/30 0:29
 */
class MusicShare(
    kind: String?, title: String, summary: String,
    jumpUrl: String,
    pictureUrl: String,
    musicUrl: String,
    brief: String
) : MessageItem(MessageItemType.MusicShare) {
    /**
     * 类型
     */
    @SerializedName(KIND)
    val kind: MusicShareKind

    /**
     * 标题
     */
    @SerializedName(TITLE)
    val title: String

    /**
     * 概括
     */
    @SerializedName(SUMMARY)
    val summary: String

    /**
     * 跳转路径
     */
    @SerializedName(JUMP_URL)
    val jumpUrl: String

    /**
     * 封面路径
     */
    @SerializedName(PICTURE_URL)
    val pictureUrl: String

    /**
     * 音源路径
     */
    @SerializedName(MUSIC_URL)
    val musicUrl: String

    /**
     * 简介
     */
    @SerializedName(BRIEF)
    val brief: String
    override fun toPlainText(): String {
        return String.format("音乐分享(%s)", title)
    }

    enum class MusicShareKind {
        NeteaseCloudMusic, QQMusic, MiguMusic
    }

    init {
        this.kind = MusicShareKind.valueOf(kind!!)
        this.title = title
        this.summary = summary
        this.jumpUrl = jumpUrl
        this.pictureUrl = pictureUrl
        this.musicUrl = musicUrl
        this.brief = brief
    }
}