package com.example.vehicleclaimapp_userstory12.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vehicleclaimapp_userstory12.R;
import com.example.vehicleclaimapp_userstory12.adapter.CompanyAdapter;
import com.example.vehicleclaimapp_userstory12.model.CompanyUpdate;
import com.example.vehicleclaimapp_userstory12.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://677636ef12a55a9a7d0ae13a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //service object
        ApiService apiService = retrofit.create(ApiService.class);

        // Make the API call
        //enqueue -asynchronous send the request and notify callback of its response or if it is any error occured, call the onFailure method
        // async call to API method,
        apiService.getCompanyUpdates().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
               // List<String> companyData = new ArrayList<>();
                if (response.isSuccessful()) {
                    CompanyAdapter companyAdapter = new CompanyAdapter((List<CompanyUpdate>) response.body());
                    recyclerView.setAdapter(companyAdapter);

                   // listView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }

                 });
    }
}
