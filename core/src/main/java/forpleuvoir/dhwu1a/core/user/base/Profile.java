package forpleuvoir.dhwu1a.core.user.base;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.IJsonData;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * bot资料
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user.bot.base
 * <p>#class_name Profile
 * <p>#create_time 2021/6/28 22:32
 */
public class Profile implements IJsonData {
    /**
     * 昵称
     */
    @SerializedName(NICKNAME)
    public final String nickname;
    /**
     * 邮箱
     */
    @SerializedName(EMAIL)
    public final String email;
    /**
     * 年龄
     */
    @SerializedName(AGE)
    public final Integer age;
    /**
     * 等级
     */
    @SerializedName(LEVEL)
    public final Integer level;
    /**
     * 标记
     */
    @SerializedName(SIGN)
    public final String sign;
    /**
     * 性别
     */
    @SerializedName(SEX)
    public final String sex;

    public Profile(JsonObject jsonObject) {
        this.nickname = jsonObject.get(NICKNAME).getAsString();
        this.email = jsonObject.get(EMAIL).getAsString();
        this.age = jsonObject.get(AGE).getAsInt();
        this.level = jsonObject.get(LEVEL).getAsInt();
        this.sign = jsonObject.get(SIGN).getAsString();
        this.sex = jsonObject.get(SEX).getAsString();
    }

    public Profile(String nickname, String email, int age, int level, String sign, String sex) {
        this.nickname = nickname;
        this.email = email;
        this.age = age;
        this.level = level;
        this.sign = sign;
        this.sex = sex;
    }

}
