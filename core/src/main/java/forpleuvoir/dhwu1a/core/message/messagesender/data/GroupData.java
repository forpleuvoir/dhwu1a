package forpleuvoir.dhwu1a.core.message.messagesender.data;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import static forpleuvoir.dhwu1a.core.websocket.base.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.messagesender.data
 * <p>#class_name GroupData
 * <p>#create_time 2021/7/1 22:24
 */
public class GroupData {
    @SerializedName(ID)
    public Long id;
    @SerializedName(NAME)
    public String name;
    @SerializedName(PERMISSION)
    public Permission permission;
}
