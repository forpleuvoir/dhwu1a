package forpleuvoir.dhwu1a.core.user;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.base.User;

import static forpleuvoir.dhwu1a.core.common.ApiKey.NICKNAME;
import static forpleuvoir.dhwu1a.core.common.ApiKey.REMARK;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name Friend
 * <p>#create_time 2021/6/29 22:44
 */
public class Friend extends User {
    /**
     * 好友的昵称
     */
    @SerializedName(NICKNAME)
    public final String nickname;
    /**
     * 好友的备注
     */
    @SerializedName(REMARK)
    public final String remark;

    protected Friend(Long id, String nickname, String remark) {
        super(id);
        this.nickname = nickname;
        this.remark = remark;
    }
}
