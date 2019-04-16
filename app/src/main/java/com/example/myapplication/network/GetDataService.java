package com.example.myapplication.network;

import com.example.myapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/api/products")
    Call<List<Product>> getAllProducts();
}
