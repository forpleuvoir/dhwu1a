package forpleuvoir.dhwu1a.core.message.messageitem;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messageitem
 * <p>#class_name Face
 * <p>#create_time 2021/6/30 0:25
 */
public class Face extends MessageItem {

    /**
     * QQ表情编号，可选，优先高于name
     */
    @SerializedName(FACE_ID)
    public final int faceId;
    /**
     * QQ表情拼音，可选
     */
    @SerializedName(NAME)
    public final String name;

    public Face(int faceId, String name) {
        super(MessageItemType.Face);
        this.faceId = faceId;
        this.name = name;
    }

    @Override
    public String toPlainText() {
        return null;
    }
}
