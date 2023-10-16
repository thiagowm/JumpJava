package com.jump.test.data;

import com.jump.test.model.ExitResponse;
import com.jump.test.model.LoginResponse;
import com.jump.test.model.ManualResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("posts/")
    Call<List<String>> findAllVehicles();

    @POST("user/login")
    Call<LoginResponse> login( @Query("email") String email, @Query("password") String password );

    @GET("{userId}/establishment/{establishmentId}/sync/manual")
    Call<ManualResponse> getManual(
        @Header("Authorization") String token,
        @Path("userId") Integer userId,
        @Path("establishmentId") Integer establishmentId
    );

    @POST("{userId}/establishment/{establishmentId}/session/close/{sessionId}")
    Call<ExitResponse> exitApp(
            @Header("Authorization") String token,
            @Path("userId") Integer userId,
            @Path("establishmentId") Integer establishmentId,
            @Path("sessionId") Integer sessionId
    );


}
