package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteban on 4/10/16.
 */
public class Result {

    @SerializedName("gender")
    public String gender;

    @SerializedName("name")
    public Name name;

    @SerializedName("location")
    public Location location;

    @SerializedName("email")
    public String email;

    @SerializedName("login")
    public Login login;

    @SerializedName("registerd")
    public String registered;

    @SerializedName("dob")
    public String dob;

    @SerializedName("phone")
    public String phone;

    @SerializedName("cell")
    public String cell;

    @SerializedName("id")
    public Id id;

    @SerializedName("picture")
    public Picture picture;

    @SerializedName("nat")
    public String nat;
}
