package cn.itcast.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    //系统用户在session中的标识符SYS_USER
    public static String USER="SYS_USER";


    /*****************
     * 系统权限集合
     ************************/
    public static String PRIVILEGE_XZGL = "xzgl";  //行政管理
    public static String PRIVILEGE_HQFW = "hqfw";  //后勤服务
    public static String PRIVILEGE_ZXXX = "zxxx";  //在线学习
    public static String PRIVILEGE_NSFW = "nsfw";  //纳税服务
    public static String PRIVILEGE_SPACE = "spaces";  //我的空间

    public static Map<String, String> PRIVILEGE_MAP;

    static {
        PRIVILEGE_MAP = new HashMap<String, String>();
        PRIVILEGE_MAP.put("xzgl", "行政管理");
        PRIVILEGE_MAP.put("hqfw", "后勤服务");
        PRIVILEGE_MAP.put("zxxx", "在线学习");
        PRIVILEGE_MAP.put("nsfw", "纳税服务");
        PRIVILEGE_MAP.put("spaces", "我的空间");

    }

}

