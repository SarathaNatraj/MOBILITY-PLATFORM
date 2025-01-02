package com.example.vehicleclaimapp_userstory12.service;

import com.example.vehicleclaimapp_userstory12.model.CompanyUpdate;
import com.example.vehicleclaimapp_userstory12.model.Policy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("company")
    Call<List<CompanyUpdate>> getCompanyUpdates();

    @GET("policy")
    Call<List<Policy>> getPolicyDeatils();
}
