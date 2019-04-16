package com.example.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    // declaramos uma variável retrofit
    private static Retrofit retrofit;
    // definimos a url que no caso e IP do meu computador onde está rodando o back-end em Node Js
    private static final String BASE_URL = "http://192.168.0.12:4000";

    // Retorna a instância do retrofit
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}