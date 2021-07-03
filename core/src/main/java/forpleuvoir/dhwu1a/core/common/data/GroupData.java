package forpleuvoir.dhwu1a.core.common.data;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import static forpleuvoir.dhwu1a.core.common.ApiKey.NAME;
import static forpleuvoir.dhwu1a.core.common.ApiKey.PERMISSION;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.common.data
 * <p>#class_name GroupData
 * <p>#create_time 2021/7/3 21:58
 */
public class GroupData extends UserData<Group> {
    /**
     * 群名称
     */
    @SerializedName(NAME)
    public final String name;
    /**
     * Bot在群里的权限
     */
    @SerializedName(PERMISSION)
    public final Permission permission;

    public GroupData(Long id, String name, Permission permission) {
        super(id);
        this.name = name;
        this.permission = permission;
    }

    @Override
    public Group getUser() {
        return bot.getGroup(id);
    }
}
