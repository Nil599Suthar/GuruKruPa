package com.example.gurukrupa.RetrofitUrl.Api;



import com.example.gurukrupa.Api_Models.bookd;
import com.example.gurukrupa.Api_Models.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface APIInterface {


    String BASE_URL="http://vinayakwebtech.com/gcandroid_demo/";

    @GET("booked_data.php")
    Call<List<bookd>> getUser();
//    //Otp
//
//
//    @POST("/MHTWS/services/generateOtpForMHTMember")
//    Call<GenerateOTPResponseModel> sendSMSusingApi(@Body GenerateOTPRequestModel requestModel);
//
//
//    @POST("/MHTWS/services/generateOtpForMHTMember")
//    Call<GenerateOTPResponseModel> sendSMSusingApiLive(@Body GenerateOTPRequestModel requestModel);


}