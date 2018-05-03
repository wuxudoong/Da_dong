package com.uikit;

public class StringUtil {
    private static String[] chineseNum = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

    public static boolean isEmpty(String s) {
        return (s == null || s.length() == 0);
    }


    public static int getChineseLen(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 将阿拉伯数字转换为中文数字
     * 支持范围 1<= num <= 99
     * created by ChenChengle
     *
     * @param num e.g. 45
     * @return e.g. 四十五
     */
    public static String convertMathNum2ChineseNum(int num) {
        StringBuilder sb = new StringBuilder();
        if (num % 10 == 0) {
            int temp = num / 10;
            if (temp > 1) {
                sb.append(chineseNum[temp - 1]);
            }
            sb.append(chineseNum[9]);
        } else {
            int temp = num / 10;
            if (num > 20) {
                sb.append(chineseNum[temp - 1]);
            }
            if (num > 10) {
                sb.append(chineseNum[9]);
            }
            sb.append(chineseNum[num % 10 - 1]);
        }
        return sb.toString();
    }

    public static String getShorNum(String s, int n) {
        if (s == null) return "";

        int pos = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                if ((i + n + 1) < s.length()) {
                    pos = i + n + 1;
                }
                break;
            }
        }
        if (pos != -1) {
            if (n == 0 && pos > 1) {
                return s.substring(0, pos - 1);
            }
            return s.substring(0, pos);
        }
        return s;
    }
}
