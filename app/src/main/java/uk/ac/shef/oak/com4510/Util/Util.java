package uk.ac.shef.oak.com4510.Util;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Util {

    private static StringBuilder str = new StringBuilder();

    public static String arrayListToString(List<Double[]> li){


        for (Double[] data : li){
            str.append(data[0]);
            str.append(",");
            str.append(data[1]);
            str.append(";");
        }
        return str.toString();
    }

    public static List<LatLng> stringToLinkList(String str){
        List<LatLng> li = new LinkedList<>();
        String[] split = str.split(";");
        for (String s : split){
            String[] split1 = s.split(",");
            LatLng latLng = new LatLng(Double.parseDouble(split1[0]), Double.parseDouble(split1[1]));
            li.add(latLng);
        }
        return li;
    }




    public static void makeNote(Activity activity, Context context, String str){
        Display display = activity.getWindowManager().getDefaultDisplay();
        int height = display.getHeight();

        Toast toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, height / 4);
        toast.show();
    }

    public static String TimeStampToString(String timeStamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timeStamp));
        String format = simpleDateFormat.format(calendar.getTimeInMillis());
        return format;
    }

}
