package com.qgj.juan_05.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static String REGEX_CHINESE = "[^(\\u4e00-\\u9fa5)]";// 中文正则 [^(\\u4e00-\\u9fa5)]

    public static String FormatString(String str){
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("");
    }
}
