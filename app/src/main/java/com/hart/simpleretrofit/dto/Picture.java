package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteban on 4/10/16.
 */
public class Picture {

    @SerializedName("large")
    public String large;

    @SerializedName("medium")
    public String medium;

    @SerializedName("thumbnail")
    public String thumbnail;

}
