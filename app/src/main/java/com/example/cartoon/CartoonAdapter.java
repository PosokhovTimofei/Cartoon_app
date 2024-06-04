package com.example.cartoon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartoonAdapter extends RecyclerView.Adapter<CartoonAdapter.CartoonViewHolder> implements Filterable {

    private List<Cartoon> cartoonList;
    private List<Cartoon> cartoonListFull;

    public CartoonAdapter() {
        this.cartoonList = new ArrayList<>();
        this.cartoonListFull = new ArrayList<>(cartoonList);
    }

    public void setCartoonList(List<Cartoon> cartoonList) {
        this.cartoonList = cartoonList;
        this.cartoonListFull = new ArrayList<>(cartoonList);
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
        holder.textViewName.setText(cartoon.name);
        holder.textViewDescription.setText(cartoon.description);
        holder.textViewSeasons.setText("Seasons: " + cartoon.seasons);
        holder.textViewAge.setText(cartoon.age);
        holder.textViewCharacter1.setText(cartoon.character1);
        holder.textViewCharacter2.setText(cartoon.character2);
        holder.textViewCharacter3.setText(cartoon.character3);
    }

    @Override
    public int getItemCount() {
        return cartoonList.size();
    }

    @Override
    public Filter getFilter() {
        return cartoonFilter;
    }

    private Filter cartoonFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Cartoon> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(cartoonListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Cartoon cartoon : cartoonListFull) {
                    if (cartoon.name.toLowerCase().contains(filterPattern) ||
                            cartoon.character1.toLowerCase().contains(filterPattern) ||
                            cartoon.character2.toLowerCase().contains(filterPattern) ||
                            cartoon.character3.toLowerCase().contains(filterPattern)) {
                        filteredList.add(cartoon);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cartoonList.clear();
            cartoonList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    static class CartoonViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewSeasons;
        TextView textViewAge;
        TextView textViewCharacter1;
        TextView textViewCharacter2;
        TextView textViewCharacter3;

        public CartoonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewSeasons = itemView.findViewById(R.id.textViewSeasons);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewCharacter1 = itemView.findViewById(R.id.textViewCharacter1);
            textViewCharacter2 = itemView.findViewById(R.id.textViewCharacter2);
            textViewCharacter3 = itemView.findViewById(R.id.textViewCharacter3);
        }
    }
}

