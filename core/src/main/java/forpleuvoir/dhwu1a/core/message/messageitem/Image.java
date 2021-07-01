package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Image
 * <p>#create_time 2021/6/30 0:26
 */
public class Image extends MessageItem {
    /**
     * 图片的imageId，群图片与好友图片格式不同。不为空时将忽略 {@link #url} 属性
     */
    @SerializedName(IMAGE_ID)
    public final String imageId;
    /**
     * 图片的URL，发送时可作网络图片的链接；接收时为腾讯图片服务器的链接，可用于图片下载
     */
    @SerializedName(URL)
    public final String url;
    /**
     * 图片的路径，发送本地图片，相对路径于plugins/MiraiAPIHTTP/images
     */
    @SerializedName(PATH)
    public final String path;
    /**
     * 图片的 Base64 编码
     */
    @SerializedName(BASE64)
    public final String base64;

    public Image(String imageId, String url, String path, String base64) {
        super(MessageItemType.Image);
        this.imageId = imageId;
        this.url = url;
        this.path = path;
        this.base64 = base64;
    }


    @Override
    public String toPlainText() {
        return "[图片]";
    }
}
