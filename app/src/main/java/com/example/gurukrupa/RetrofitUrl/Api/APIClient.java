package com.example.gurukrupa.RetrofitUrl.Api;


import com.example.gurukrupa.BuildConfig;
import com.example.gurukrupa.StartUp.APILinks;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIClient {

        public static final String BASE_URL = APILinks.RootURL;
        private static Retrofit retrofit = null;

    public static Retrofit getClient() {


        OkHttpClient.Builder okClientBuilder=new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        if(BuildConfig.DEBUG){
            okClientBuilder.addInterceptor(logging);

        }



            if (retrofit==null) {



                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(100 , TimeUnit.SECONDS)
                        .readTimeout(100 ,TimeUnit.SECONDS).build();


                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }











}
