package com.example.finalyearproject.api_response;

import com.example.finalyearproject.models.SignData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SignDataApiResponse {
    @GET("api/all-sign-data")
    Call<List<SignData>> getSignData();
}