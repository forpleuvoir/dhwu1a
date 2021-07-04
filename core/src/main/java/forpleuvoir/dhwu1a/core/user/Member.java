package forpleuvoir.dhwu1a.core.user;

import forpleuvoir.dhwu1a.core.user.base.User;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name Member
 * <p>#create_time 2021/7/1 21:59
 */
public class Member extends User {
    /**
     * 群员所在群
     */
    public final Group group;

    protected Member(Long id, Group group) {
        super(id);
        this.group = group;
    }


    public Group getGroup() {
        return group;
    }
}
