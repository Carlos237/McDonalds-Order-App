package com.example.orderapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String nameofburger = modelList.get(position).getmburgerName();
        String descriptionofburger = modelList.get(position).getmburgerDetail();
        int images = modelList.get(position).getmburgerPhoto();

        holder.mburgerName.setText(nameofburger);
        holder.mburgerDescription.setText(descriptionofburger);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView mburgerName, mburgerDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mburgerName = itemView.findViewById(R.id.coffeeName);
            mburgerDescription = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.coffeeImage);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            // lets get the position of the view in list and then work on it

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, BigMacActivity.class);
                context.startActivity(intent);
            }

            if (position == 1) {
                Intent intent2 = new Intent(context, McPolloActivity.class);
                context.startActivity(intent2);
            }
            if (position == 2) {
                Intent intent3 = new Intent(context, CboActivity.class);
                context.startActivity(intent3);
            }
            if (position == 3) {
                Intent intent4 = new Intent(context, RoyalDeluxeActivity.class);
                context.startActivity(intent4);
            }
            if (position == 4) {
                Intent intent5 = new Intent(context, WrapActivity.class);
                context.startActivity(intent5);
            }
            if (position == 5) {
                Intent intent6 = new Intent(context, ExtremeActivity.class);
                context.startActivity(intent6);
            }
            if (position == 6) {
                Intent intent7 = new Intent(context, SignatureActivity.class);
                context.startActivity(intent7);
            }
            if (position == 7) {
                Intent intent8 = new Intent(context, ChickenSupremeActivity.class);
                context.startActivity(intent8);
            }
            if (position == 8) {
                Intent intent9 = new Intent(context, DoubleCheeseActivity.class);
                context.startActivity(intent9);
            }
        }
    }
}