package com.hart.simpleretrofit.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Esteban on 4/10/16.
 */
public class GetRandomUserResponse {

    @SerializedName("results")
    public List<Result> resultList;

    @SerializedName("info")
    public Info info;
}
