package com.example.orderapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.orderapp.Database.OrderContract;

public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // getting theviews

        TextView burgerName, yesDrink, yesFries, price, quantity, typeCarne;


        burgerName = view.findViewById(R.id.burgerNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        yesDrink = view.findViewById(R.id.hasDrink);
        yesFries = view.findViewById(R.id.hasFries);
        typeCarne = view.findViewById(R.id.hasType);
        quantity = view.findViewById(R.id.quantityinOrderSummary);

        // getting the values by first getting the position of their columns

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofburger = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofburger = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
        int hasDrink = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_DRINK);
        int hasFries = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_FRIES);
        int hasCarne = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_TYPE);

        String nameofburger = cursor.getString(name);
        String pricesofburger = cursor.getString(priceofburger);
        String quantitysofburger = cursor.getString(quantityofburger);
        String yeshasFries = cursor.getString(hasFries);
        String yeshasDrink = cursor.getString(hasDrink);
        String yeshasCarne = cursor.getString(hasCarne);



        burgerName.setText(nameofburger);
        price.setText(pricesofburger);
        yesFries.setText(yeshasFries);
        yesDrink.setText(yeshasDrink);
        quantity.setText(quantitysofburger);
        typeCarne.setText(yeshasCarne);





    }
}
