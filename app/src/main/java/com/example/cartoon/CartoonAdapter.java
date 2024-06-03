package com.example.cartoon;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartoonAdapter extends RecyclerView.Adapter<CartoonAdapter.CartoonViewHolder> {

    private List<Cartoon> cartoonList;



    public CartoonAdapter() {
        this.cartoonList = new ArrayList<>();
    }

    public void setCartoonList(List<Cartoon> cartoonList) {
        this.cartoonList = cartoonList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cartoon, parent, false);
        return new CartoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartoonViewHolder holder, int position) {
        Cartoon cartoon = cartoonList.get(position);
        Picasso.get().load(cartoon.image).into(holder.imageView);
        holder.textView.setText(cartoon.name);
    }

    @Override
    public int getItemCount() {
        return cartoonList.size();
    }

    static class CartoonViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public CartoonViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}

