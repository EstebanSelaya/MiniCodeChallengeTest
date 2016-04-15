package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteban on 4/10/16.
 */
public class Info {

    @SerializedName("seed")
    String seed;

    @SerializedName("results")
    String results;

    @SerializedName("page")
    String page;

    @SerializedName("version")
    String version;
}
