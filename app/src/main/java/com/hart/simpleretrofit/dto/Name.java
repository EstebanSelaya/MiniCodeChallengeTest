package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteban on 4/10/16.
 */
public class Name {

    @SerializedName("title")
    public String title;

    @SerializedName("first")
    public String first;

    @SerializedName("last")
    public String last;

    public String getFormattedName(){
        return String.format("%s. %s %s", firstCaps(title), firstCaps(first), firstCaps(last));
    }

    public static String firstCaps(String inStr)
    {
        if (inStr != null && inStr.length() > 0)
        {
            char[] outStr = inStr.toCharArray();
            outStr[0] = Character.toUpperCase(outStr[0]);
            return String.valueOf(outStr);
        }
        else
            return inStr;
    }
}
