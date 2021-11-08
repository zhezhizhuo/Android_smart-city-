package com.qgj.juan_05.util;

import android.content.Context;

public class SizeUtils {

    public static int dipBox(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5F);
    }
}
