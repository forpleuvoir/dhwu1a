package forpleuvoir.dhwu1a.core.user;

import forpleuvoir.dhwu1a.core.user.base.User;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name Group
 * <p>#create_time 2021/7/1 21:58
 */
public class Group extends User {

    private final ConcurrentLinkedDeque<Member> members = new ConcurrentLinkedDeque<>();

    protected Group(Long id) {
        super(id);
    }

    @Nullable
    public Member getMember(Long id) {
        return members.stream().filter(member -> member.id.equals(id)).findFirst().orElse(null);
    }
}
