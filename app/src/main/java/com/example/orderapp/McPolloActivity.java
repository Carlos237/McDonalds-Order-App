package com.example.orderapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderapp.Database.OrderContract;

public class McPolloActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    // first of all we will get the views that are  present in the layout of info

    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, drinnkName, coffeePrice, drinnkDescription;
    RadioButton rb;
    CheckBox addFries, addaddDrink;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        drinnkName = findViewById(R.id.burgerNameinInfo);
        drinnkDescription = findViewById(R.id.descriptioninfo);
        coffeePrice = findViewById(R.id.coffeePrice);
        addFries = findViewById(R.id.addFries);
        addtoCart = findViewById(R.id.addtocart);
        addaddDrink = findViewById(R.id.addDrink);
        rb = (RadioButton) findViewById(R.id.hotradioButton);

        // setting the name of burger

        drinnkName.setText("MC Pollo");
        drinnkDescription.setText(R.string.mcpollo);
        imageView.setImageResource(R.drawable.mcpollo);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(McPolloActivity.this, SummaryActivity.class);
                startActivity(intent);
                // once this button is clicked we want to save our values in the database and send those values
                // right away to summary activity where we display the order info

                SaveCart();
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // burger price
                int basePrice = 7;
                quantity++;
                displayQuantity();
                int coffePrice = basePrice * quantity;
                String setnewPrice = String.valueOf(coffePrice);
                coffeePrice.setText(setnewPrice);


                // checkBoxes functionality

                int ifCheckBox = CalculatePrice(addaddDrink, addFries);
                coffeePrice.setText(ifCheckBox + "€ ");

            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 7;
                // because we dont want the quantity go less than 0
                if (quantity <= 0) {
                    Toast.makeText(McPolloActivity.this, "No se puede poner una cantidad < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int coffePrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(coffePrice);
                    coffeePrice.setText(setnewPrice);


                    // checkBoxes functionality

                    int ifCheckBox = CalculatePrice(addaddDrink, addFries);
                    coffeePrice.setText(ifCheckBox + "€ ");
                }
            }
        });



    }

    private boolean SaveCart() {

        // getting the values from our views
        String name = drinnkName.getText().toString();
        String price = coffeePrice.getText().toString();
        String quantity = quantitynumber.getText().toString();
        String carne = rb.getText().toString();
        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);
        values.put(OrderContract.OrderEntry.COLUMN_TYPE, carne);
        if(rb.isChecked()) {
            values.put(OrderContract.OrderEntry.COLUMN_TYPE, "Al punto: SÍ");
        }else{
            values.put(OrderContract.OrderEntry.COLUMN_TYPE, "Al punto: NO");
        }

        if (addaddDrink.isChecked()) {
            values.put(OrderContract.OrderEntry.COLUMN_FRIES, "Patatas grandes: SÍ");
        } else {
            values.put(OrderContract.OrderEntry.COLUMN_FRIES, "Patatas grandes: NO");

        }

        if (addFries.isChecked()) {
            values.put(OrderContract.OrderEntry.COLUMN_DRINK, "Bebida: SÍ");
        } else {
            values.put(OrderContract.OrderEntry.COLUMN_DRINK, "Bebida: NO");

        }

        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri==null) {
                Toast.makeText(this, "Fallo al añadir al pedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Éxito!  Añadido al pedido", Toast.LENGTH_SHORT).show();

            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }

    private int CalculatePrice(CheckBox addaddDrink, CheckBox addFries) {

        int basePrice = 5;

        if (addaddDrink.isChecked()) {
            // add the cream cost $2
            basePrice = basePrice + 1;
        }

        if (addFries.isChecked()) {
            //
            basePrice = basePrice + 3;
        }

        return basePrice * quantity;
    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,
                OrderContract.OrderEntry.COLUMN_FRIES,
                OrderContract.OrderEntry.COLUMN_DRINK
        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
            int hasFries = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_FRIES);
            int hasDrink = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_DRINK);
            int hasCarne = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_TYPE);

            String nameofburger = cursor.getString(name);
            String pricesofburger = cursor.getString(price);
            String quantitysofburger = cursor.getString(quantity);
            String yeshasFries = cursor.getString(hasFries);
            String yeshasDrink = cursor.getString(hasDrink);
            String yeshasCarne = cursor.getString(hasCarne);

            drinnkName.setText(nameofburger);
            coffeePrice.setText(price);
            quantitynumber.setText(quantity);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        drinnkName.setText("");
        coffeePrice.setText("");
        quantitynumber.setText("");

    }
}