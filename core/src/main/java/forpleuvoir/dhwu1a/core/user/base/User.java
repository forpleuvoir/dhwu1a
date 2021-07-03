package forpleuvoir.dhwu1a.core.user.base;

import com.google.gson.annotations.SerializedName;

import static forpleuvoir.dhwu1a.core.common.ApiKey.ID;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name User
 * <p>#create_time 2021/6/29 22:32
 */
public abstract class User implements IUser {
    /**
     * 用户ID(QQ号)
     */
    @SerializedName(ID)
    public final Long id;

    protected User(Long id) {
        this.id = id;
    }
}
