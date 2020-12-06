package com.bankaks.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
    public static boolean hasNetwork(Context context){



        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connec.getActiveNetworkInfo();

        boolean hasNetwork=false;
        // Check for network connections

        hasNetwork= activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return hasNetwork;

    }

    public static String nullCheck(String str){
        if(str==null|str.equals("null")||str.equals("")||str.equals(" ")||str.isEmpty())
            str="";
        return str;
    }

}
