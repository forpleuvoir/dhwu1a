package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Voice
 * <p>#create_time 2021/6/30 0:27
 */
public class Voice extends MessageItem {
    /**
     * 语音的voiceId，不为空时将忽略url属性
     */
    @SerializedName(VOICE_ID)
    public final String voiceId;
    /**
     * 语音的URL，发送时可作网络语音的链接；接收时为腾讯语音服务器的链接，可用于语音下载
     */
    @SerializedName(URL)
    public final String url;
    /**
     * 语音的路径，发送本地语音，相对路径于plugins/MiraiAPIHTTP/voices
     */
    @SerializedName(PATH)
    public final String path;
    /**
     * 语音的 Base64 编码
     */
    @SerializedName(BASE64)
    public final String base64;

    public Voice(String voiceId, String url, String path, String base64) {
        super(MessageItemType.Voice);
        this.voiceId = voiceId;
        this.url = url;
        this.path = path;
        this.base64 = base64;
    }

    @Override
    public String toPlainText() {
        return "[语音消息]";
    }
}
