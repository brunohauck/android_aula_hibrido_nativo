package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Product;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CustomViewHolder> {
    private List<Product> dataList;
    private Context context;
    // Nosso construtor que recebe a lista de produtos
    public ProductAdapter(Context context, List<Product> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {
        //Custom View Holder vai somente declarar view e TextView Image View
        public final View mView;
        TextView txtTitle;
        private ImageView coverImage;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
        }
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Infla o layout da nossa lista personalizada
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        //OnBindViewHolder via pegas as informações da lista
        //e seta com as informações do objeto
        // No caso pegou a posicao da lista já populada e executou um getProductName
        // pegou o nome do produto e setou no txtTitle do nosso layout
        holder.txtTitle.setText(dataList.get(position).getProductName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImg_url())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
