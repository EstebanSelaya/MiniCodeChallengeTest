package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteban on 4/10/16.
 */
public class Location {

    @SerializedName("street")
    public String street;

    @SerializedName("city")
    public String city;

    @SerializedName("state")
    public String state;

    @SerializedName("postcode")
    public String postcode;
}
