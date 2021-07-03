package forpleuvoir.dhwu1a.core.common.data;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.Friend;

import static forpleuvoir.dhwu1a.core.common.ApiKey.NICKNAME;
import static forpleuvoir.dhwu1a.core.common.ApiKey.REMARK;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.common.data
 * <p>#class_name FriendData
 * <p>#create_time 2021/7/3 21:53
 */
public class FriendData extends UserData<Friend> {
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

    public FriendData(Long id, String nickname, String remark) {
        super(id);
        this.nickname = nickname;
        this.remark = remark;
    }

    @Override
    public Friend getUser() {
        return bot.getFriend(this.id);
    }
}
