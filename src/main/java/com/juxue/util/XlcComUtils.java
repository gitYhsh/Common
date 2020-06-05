package com.juxue.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class XlcComUtils {

    public XlcComUtils() {
    }

    /**获取UUID 做为数据库各个表的主键,不采用自增长*/
    public static String getUUID (){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
    /**自定义生成得部门id**/
    public static String getRandomDeptId(){
        int num = (int)((Math.random()*9+1)*100000);
        return "00"+String.valueOf(num);
    }

    /**格式化星期**/
    public static String formatterWeek(String week){
        String formatter  = "";
        try {
            switch (week){
                case "1":
                    formatter =  "一";
                    break;
                case "2":
                    formatter = "二";
                    break;
                case "3":
                    formatter = "三";
                    break;
                case "4":
                    formatter = "四";
                    break;
                case "5":
                    formatter = "五";
                    break;
                case "6":
                    formatter = "六";
                    break;
                default:
                    formatter = "日";
            }

        }catch (Exception e){

        }
        return formatter;
    }


    /**
     * 简单判断是否为手机号
     *
     * @param phoneNo 手机号
     * @return boolean
     */
    public static boolean isPhoneNo(String phoneNo) {
        String regex = "[1]\\d{10}";
        if (StringUtils.isBlank(phoneNo))
            return false;
        else
            return phoneNo.matches(regex);
    }

    /**表情包 和一些特殊字符 处理**/
    public static String filterEmoji(String source) {
        int len = source.length();
        StringBuilder buf = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            } else{
                buf.append("*");
            }
        }
        return buf.toString();
    }

    /**處理表情包**/
    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) ||
                (codePoint == 0xA) || (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

}
