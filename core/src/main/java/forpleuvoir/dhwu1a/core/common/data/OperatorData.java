package forpleuvoir.dhwu1a.core.common.data;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.common.data
 * <p>#class_name OperatorData
 * <p>#create_time 2021/7/3 22:33
 */
public class OperatorData extends UserData<Member> {
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

    public OperatorData(Long id, String memberName, String specialTitle,
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

    @Nullable
    @Override
    public Member getUser() {
        AtomicReference<Member> member = new AtomicReference<>();
        Optional.ofNullable(getGroup()).ifPresent(group1 -> member.set(group1.getMember(id)));
        return member.get();
    }

    @Nullable
    public Group getGroup() {
        return group.getUser();
    }
}
