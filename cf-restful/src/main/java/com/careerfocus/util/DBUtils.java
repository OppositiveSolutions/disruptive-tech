package com.careerfocus.util;

/**
 * Created by sarath on 04/06/17.
 */
public class DBUtils {

    public static String getMarkers(int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++)
            builder.append("?,");
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

}
