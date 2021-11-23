package com.qgj.juan_05.util;

import android.os.Bundle;

import androidx.navigation.NavController;

import com.qgj.juan_05.R;

public class PagerController {
    public static void PagerController(NavController controller,String index){
        switch (index){
            case "metro_query/index":
        //        controller.navigate(R.id.metro_fragment,new Bundle());
                break;
            case "more":
                controller.navigate(R.id.servicesFragment);
                break;
            case "ss/index":
                controller.navigate(R.id.wearther_fragment,new Bundle());
                break;
            case "bus_query/custom_shuttle":
                    controller.navigate(R.id.smartBusFragment,new Bundle());
                break;
            case "outpatient/hospitalList":
                controller.navigate(R.id.outpatientFragment);
                break;
            case "天气预报":
                controller.navigate(R.id.wearther_fragment,new Bundle());
                break;
            case "activity/index":
                controller.navigate(R.id.activityFragment,new Bundle());
                break;
            case "movie/index":
                controller.navigate(R.id.moviesfragment,new Bundle());
                break;
            case "违规查询":
                controller.navigate(R.id.wearther_fragment,new Bundle());
                break;
            case "traffic/index":
                controller.navigate(R.id.targetedFragment,new Bundle());
                break;
            case "house/index":
                controller.navigate(R.id.houseFragment,new Bundle());
                break;
            case "job/index":
                controller.navigate(R.id.targetedFragment,new Bundle());
                break;
            case "takeout/index":
                controller.navigate(R.id.tackoutFragment,new Bundle());
                break;
            case "living_expenses/index":
                controller.navigate(R.id.lifemoneyFragment,new Bundle());
                break;
            case "park/index":
                controller.navigate(R.id.targetedFragment,new Bundle());
                break;
        }
    }
}
