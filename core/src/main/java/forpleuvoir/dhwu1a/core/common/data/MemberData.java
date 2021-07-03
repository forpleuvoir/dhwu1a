package forpleuvoir.dhwu1a.core.common.data;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;
import java.util.Objects;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.common.data
 * <p>#class_name MemberData
 * <p>#create_time 2021/7/3 22:07
 */
public class MemberData extends UserData<Member> {

    /**
     * 操作者的群名片
     */
    @SerializedName(MEMBER_NAME)
    public final String memberName;
    /**
     * 群头衔
     */
    @SerializedName(SPECIAL_TITLE)
    public final String specialTitle;
    /**
     * 操作者在群中的权限，OWNER、ADMINISTRATOR或MEMBER
     */
    @SerializedName(PERMISSION)
    public final Permission permission;
    /**
     * 入群时间
     */
    @SerializedName(JOIN_TIMESTAMP)
    public final Integer joinTimestamp;
    /**
     * 最后发言事件
     */
    @SerializedName(LAST_SPEAK_TIMESTAMP)
    public final Integer lastSpeakTimestamp;
    /**
     * 禁言剩余时间
     */
    @SerializedName(MUTE_TIME_REMAINING)
    public final Integer muteTimeRemaining;
    /**
     * 所在群信息
     */
    @SerializedName(GROUP)
    public final GroupData group;

    public MemberData(Long id, String memberName, String specialTitle,
                      Permission permission,
                      Integer joinTimestamp,
                      Integer lastSpeakTimestamp,
                      Integer muteTimeRemaining,
                      GroupData group
    ) {
        super(id);
        this.memberName = memberName;
        this.specialTitle = specialTitle;
        this.permission = permission;
        this.joinTimestamp = joinTimestamp;
        this.lastSpeakTimestamp = lastSpeakTimestamp;
        this.muteTimeRemaining = muteTimeRemaining;
        this.group = group;
    }

    public Group getGroup() {
        return group.getUser();
    }

    @Nullable
    @Override
    public Member getUser() {
        try {
            return Objects.requireNonNull(group.getUser()).getMember(id);
        } catch (Exception ignored) {
            return null;
        }
    }
}
