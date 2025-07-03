package com.example.jobly;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:3000";
    private static Retrofit retrofit = null;



    public static Retrofit getClient(String token) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    // إضافة توكن التوثيق في الهيدر
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .addHeader("Accept", "application/json") // إضافة نوع البيانات
                            .build();
                    return chain.proceed(request);
                }).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client) // تمرير الـ OkHttpClient
                    .addConverterFactory(GsonConverterFactory.create()) // تحويل البيانات باستخدام Gson
                    .build();
        }
        return retrofit;
    }
}
