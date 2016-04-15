package com.hart.simpleretrofit;

import com.hart.simpleretrofit.dto.GetRandomUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Esteban on 4/10/16.
 */

public interface API {

    @GET("/api/")
    Call<GetRandomUserResponse> getRandomUser();

    @GET("/api/")
    Call<GetRandomUserResponse> getRandomUsers(@Query("results") int numResults);
}
