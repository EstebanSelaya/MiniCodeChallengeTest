package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Esteban on 4/10/16.
 */
public class Login {

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    @SerializedName("salt")
    String salt;

    @SerializedName("md5")
    String md5;

    @SerializedName("sha1")
    String sha1;

    @SerializedName("sha256")
    String sha256;
}
