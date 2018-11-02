package com.iteso.proyectoexamencliente;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iteso.proyectoexamencliente.beans.Category;
import com.iteso.proyectoexamencliente.beans.City;
import com.iteso.proyectoexamencliente.beans.ItemProduct;
import com.iteso.proyectoexamencliente.beans.Store;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    ArrayList<ItemProduct> itemProducts, tech, elec, home;
    RecyclerView recyclerView;
    AdapterItemProduct adapterItemProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.activity_main_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        refresh();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void refresh() {
        ItemProduct itemProduct = new ItemProduct();
        Store store;
        City city;
        Category category;
        itemProducts = new ArrayList<>();
        tech = new ArrayList<>();
        elec = new ArrayList<>();
        home = new ArrayList<>();

        Uri uri = Uri.parse("content://com.iteso.ProyectoExamen.content");
        String[] projection = {"Products"};

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, null, null, null);

        while(cursor.moveToNext()){
            itemProduct = new ItemProduct();
            store = new Store();
            city = new City();
            category = new Category();
            itemProduct.setTitle(cursor.getString(1));
            city.setName(cursor.getString(3));
            store.setName(cursor.getString(4));
            itemProduct.setStore(store);
            category.setId(cursor.getInt(2));
            itemProduct.setCategory(category);

            itemProducts.add(itemProduct);
        }

        try{
            cursor.close();
        }catch (Exception e){

        }

        byCategories();

        adapterItemProduct = new AdapterItemProduct(this, tech);
        recyclerView.setAdapter(adapterItemProduct);
    }

    private void byCategories() {
        ItemProduct itemProduct;
        for (int x =0 ; x<itemProducts.size(); x++) {
            itemProduct = itemProducts.get(x);
            switch (itemProduct.getCategory().getId()) {
                case 0:
                    tech.add(itemProduct);
                    break;
                case 1:
                    home.add(itemProduct);
                    break;
                case 2:
                    elec.add(itemProduct);
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_technology) {
            adapterItemProduct.setItemProducts(tech);
            recyclerView.setAdapter(adapterItemProduct);
            return true;
        }
        if(id == R.id.action_home){
            adapterItemProduct.setItemProducts(home);
            recyclerView.setAdapter(adapterItemProduct);
            return true;
        }
        if(id == R.id.action_electronics){
            adapterItemProduct.setItemProducts(elec);
            recyclerView.setAdapter(adapterItemProduct);
            return true;
        }
        if(id == R.id.action_refresh){
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
