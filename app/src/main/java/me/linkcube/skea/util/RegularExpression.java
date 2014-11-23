package me.linkcube.skea.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CXC on 14-11-23.
 */
public class RegularExpression {
    /**
     * 验证邮箱的正则表达式
     * */
    private static final String EMAIL_REGULAR_EXPRESSION="^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

    /**
     * 验证密码的正则表达式
     * 以字母开头，长度在6~18之间，只能包含字符、数字和下划线
     * */
    private static final String PASSWORD_REGULAR_EXPRESSION="^[a-zA-Z]\\w{5,17}$";

    /**
     * 匹配邮箱是否符合要求
     * @param strEmail:待验证的邮箱字符串
     * @return  : 匹配返回true ,否则返回false
     * */
    public static boolean isEmail(String strEmail) {

        Pattern p = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 匹配密码是否符合要求
     * @param strPassword:待验证的邮箱字符串
     * @return  : 匹配返回true ,否则返回false
     * */
    public static boolean isPassword(String strPassword) {
        Pattern p = Pattern.compile(PASSWORD_REGULAR_EXPRESSION);
        Matcher m = p.matcher(strPassword);
        return m.matches();
    }
}
