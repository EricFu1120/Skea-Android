package me.linkcube.skea.core.http;

/**
 * 网络请求状态码
 * <p/>
 * Created by Ervin on 14/11/20.
 */
public class SkeaRequestStatus {

    /**
     * 请求成功
     */
    public static final int SUCC = 100;

    /**
     * 参数错误
     */
    public static final int PARAM_ERROR = 101;

    /**
     * 该用户已被注册
     */
    public static final int USER_EXIST = 102;

    /**
     * 用户名或者密码错误
     */
    public static final int UNKNOWN_USER_OR_BAD_PWD = 103;

    /**
     * 结果未知道
     */
    public static final int INTERNAL_ERROR = 104;
}
