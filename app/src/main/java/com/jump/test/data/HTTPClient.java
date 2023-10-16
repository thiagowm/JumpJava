package com.jump.test.data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPClient {
    public static final String BASE_URL = "https://dev.app.jumpparkapi.com.br/api/";
    public static final String API_KEY = "thiagowebmobile@gmail.com";
    public static String email = "";
    public static String password = "";
    public static String token = "";
    public static Integer userId = 0;
    public static Integer sessionId = 0;
    public static Integer establishmentId = 0;

    private static Retrofit retrofit = null;

    public static Retrofit OkHttpClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .client( client )
                .build();

        return retrofit;
    }

}
