package forpleuvoir.dhwu1a.core.common.data;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.user.bot.Bot;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.ID;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.common.data
 * <p>#class_name UserData
 * <p>#create_time 2021/7/3 21:53
 */
public abstract class UserData<U extends User> {
    public transient final Bot bot = Dhwu1a.bot;

    @SerializedName(ID)
    public final Long id;

    public UserData(Long id) {
        this.id = id;
    }

    @Nullable
    public abstract U getUser();
}
