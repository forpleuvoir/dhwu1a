package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;
/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name MusicShare
 * <p>#create_time 2021/6/30 0:29
 */
public class MusicShare extends MessageItem {

    /**
     * 类型
     */
    @SerializedName(KIND)
    public final MusicShareKind kind;
    /**
     * 标题
     */
    @SerializedName(TITLE)
    public final String title;
    /**
     * 概括
     */
    @SerializedName(SUMMARY)
    public final String summary;
    /**
     * 跳转路径
     */
    @SerializedName(JUMP_URL)
    public final String jumpUrl;
    /**
     * 封面路径
     */
    @SerializedName(PICTURE_URL)
    public final String pictureUrl;
    /**
     * 音源路径
     */
    @SerializedName(MUSIC_URL)
    public final String musicUrl;
    /**
     * 简介
     */
    @SerializedName(BRIEF)
    public final String brief;

    public MusicShare(String kind, String title, String summary,
                      String jumpUrl,
                      String pictureUrl,
                      String musicUrl,
                      String brief
    ) {
        super(MessageItemType.MusicShare);
        this.kind = MusicShareKind.valueOf(kind);
        this.title = title;
        this.summary = summary;
        this.jumpUrl = jumpUrl;
        this.pictureUrl = pictureUrl;
        this.musicUrl = musicUrl;
        this.brief = brief;
    }

    @Override
    public String toPlainText() {
        return String.format("音乐分享(%s)", title);
    }

    public enum MusicShareKind {
        NeteaseCloudMusic,
        QQMusic,
        MiguMusic
    }
}
