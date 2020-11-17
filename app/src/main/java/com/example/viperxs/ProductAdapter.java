package com.example.viperxs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.productList = (ArrayList<Product>)objects;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    //TODO:НЕРАБОТАЕТ АДАПТЕР
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.product_item_calc, parent, false);
        }

        Product p = getItem(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        ((TextView) view.findViewById(R.id.product_item_name)).setText(p.productName);
        ((TextView) view.findViewById(R.id.product_item_price)).setText((int) p.productPrise);
        ((TextView) view.findViewById(R.id.product_item_weight)).setText((int) p.productWeight);
        ((TextView) view.findViewById(R.id.product_item_res)).setText((int) p.productForOneGram);
        ((TextView) view.findViewById(R.id.product_item_res_another)).setText((int) p.productForAnotherGram);

        return view;
    }

    public ProductAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ProductAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ProductAdapter(@NonNull Context context, int resource, @NonNull Product[] objects) {
        super(context, resource, objects);
    }

    public ProductAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Product[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ProductAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Product> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
