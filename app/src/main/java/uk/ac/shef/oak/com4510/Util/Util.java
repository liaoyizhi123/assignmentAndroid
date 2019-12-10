package uk.ac.shef.oak.com4510.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    public static String[] stringToArrayList(String str){
        String[] split = str.split(";");
        System.out.println(split);
        return split;
    }


    public static void main(String[] args) {

        Double data[] = new Double[2];
        data[0] = 1.0;
        data[1] = 2.0;

        Double data2[] = new Double[2];
        data2[0] = 3.0;
        data2[1] = 4.0;

        List<Double[]> li = new ArrayList<>();
        li.add(data);
        li.add(data2);
        String s = arrayListToString(li);

        String[] s1 = stringToArrayList(s);
        System.out.println(s1[0]);



    }

}
