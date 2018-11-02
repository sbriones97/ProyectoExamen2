package com.iteso.proyectoexamencliente;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.proyectoexamencliente.beans.ItemProduct;

import java.util.List;

public class AdapterItemProduct extends RecyclerView.Adapter<AdapterItemProduct.MyViewHolder> {


    public List<ItemProduct> itemProducts;
    private Context context;

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView city;
        TextView store;

        MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.item_itemproduct_title);
            city = view.findViewById(R.id.item_itemproduct_city);
            store = view.findViewById(R.id.item_itemproduct_store);
        }
    }

    public AdapterItemProduct(List<ItemProduct> itemProducts){
        this.itemProducts = itemProducts;
    }

    public AdapterItemProduct(Context context, List<ItemProduct> itemProducts){
        this.context = context;
        this.itemProducts = itemProducts;
    }

    public List<ItemProduct> getItemProducts() {
        return itemProducts;
    }

    public void setItemProducts(List<ItemProduct> itemProducts) {
        this.itemProducts = itemProducts;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public AdapterItemProduct.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_itemproduct, parent, false);
        return new AdapterItemProduct.MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder( AdapterItemProduct.MyViewHolder myViewHolder, int position){
        ItemProduct itemProduct = itemProducts.get(position);
        //myViewHolder.nombre.setText(materiaList.get(myViewHolder.getAdapterPosition()).getNombreMateria());
        //myViewHolder.promedio.setText(Float.toString(materiaList.get(myViewHolder.getAdapterPosition()).getPromedio()));
        myViewHolder.title.setText(itemProduct.getTitle());
        //myViewHolder.city.setText(itemProduct.getStore().getCity().getName());
       // myViewHolder.store.setText(itemProduct.getStore().getName());


    }

    @Override
    public int getItemCount() {
        return itemProducts.size();
    }


}
