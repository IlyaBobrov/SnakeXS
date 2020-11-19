package com.example.viperxs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private final String LOG_TAG = "eat";

    Context context;
    LayoutInflater inflater;
    ArrayList<Product> objects;

    ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.objects = products;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(LOG_TAG, "adapter: Constructor: ProductAdapter");
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.product_item_calc, parent, false);
        }

        Product p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        ((TextView) view.findViewById(R.id.product_item_name)).setText(p.productName);
        ((TextView) view.findViewById(R.id.product_item_weight)).setText((Long.toString(p.productWeight)));
        ((TextView) view.findViewById(R.id.product_item_price)).setText((Double.toString(p.productPrise)));
        ((TextView) view.findViewById(R.id.product_item_res)).setText((Double.toString(p.productForOneGram)));
        ((TextView) view.findViewById(R.id.product_item_res_another)).setText((Double.toString(p.productForAnotherGram)));
        Log.d(LOG_TAG, "adapter: getView: EndMethod");
        return view;

    }

    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

}
