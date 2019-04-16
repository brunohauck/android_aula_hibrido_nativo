package com.example.myapplication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.model.Product;
import com.example.myapplication.network.GetDataService;
import com.example.myapplication.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
    private ProductAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    public ProductListFragment() {
        // Required empty public constructor
    }
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Product>> call = service.getAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getActivity(), "Ocorreu algum problema!", Toast.LENGTH_SHORT).show();
            }
        });
        v = inflater.inflate(R.layout.fragment_produt_list, container, false);
        return v;
    }
    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Product> photoList) {
        recyclerView = v.findViewById(R.id.customRecyclerView);
        adapter = new ProductAdapter(getActivity(),photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
