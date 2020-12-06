package com.bankaks.utils;
/**
 *  This is a Utility Class
 *
 * This has internet checking code .
 * Null check code
 *
 * @author Debashish
 * @version 2020.12.06
 * @since 1.0
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    /**
     * Method that returns Internet Connectivity
     *
     * @param context The context is passed
     * @return The function returns true or false according to internet connection
     */

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

    /**
     * Method that check null values and replaces null values to ""
     *
     * @param str The string is passed
     * @return The function returns original string if not null
     */

    public static String nullCheck(String str){
        if(str==null|str.equals("null")||str.equals("")||str.equals(" ")||str.isEmpty())
            str="";
        return str;
    }

}
