package com.gruppozenit.crocerossa.network.provider;

import com.gruppozenit.crocerossa.utils.UnsafeOkHttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitProvider {

    // private final String url = "https://213.215.194.149/service/";
    //  private final String url = "https://mobile.memc.it/service/";
    //  private final String url = "http://192.168.0.24:8088/service/"; //local
//   private final String url = "http://192.168.0.19:8015/service/"; // timi local
    // private final String url = "http://192.168.0.150:8015/service/"; // arshad local
    //  private final String url = "https://213.215.194.156/service/"; // staging server
    private final String url = "https://crinovara.gruppozenit.com/service/";
    //  private final String url = "http://192.168.0.150:8015/service/"; // new
    private Retrofit retrofit;
    private static RetrofitProvider ourInstance;
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static RetrofitProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new RetrofitProvider();
        }





        return ourInstance;
    }


    private RetrofitProvider() {

      /*  if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }*/

        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

    }

    private OkHttpClient.Builder okHttpClient(final String token) {
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("token", token).build();
                return chain.proceed(request);
            }
        })/*.readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)*/;


        return httpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


}

