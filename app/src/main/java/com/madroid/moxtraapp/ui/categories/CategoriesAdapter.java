package com.madroid.moxtraapp.ui.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

import java.util.List;

/**
 * Created by mohamed on 25/03/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{


    private final OnItemClickListener listener;
    private final Context mContext;
    private final List<AllCategoriesResponseDTO.Category> results;

    public CategoriesAdapter(List<AllCategoriesResponseDTO.Category> categories, Context context, OnItemClickListener listener) {
        this.results = categories;
        this.mContext = context;
        this.listener = listener;

    }


    public interface OnItemClickListener {
        void onItemClick(AllCategoriesResponseDTO.Category category);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizental_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        AllCategoriesResponseDTO.Category result = results.get(position);

        if (result.getName() != null) {
            holder.bind(result, listener);
            if (result.getName() != null && !result.getName().isEmpty()) {
                holder.itemText.setText(result.getName());
            } else {
                holder.itemText.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

//        public ImageView itemImage;
        public TextView itemText;

        public MyViewHolder(View view) {
            super(view);
//            itemImage = (ImageView) view.findViewById(R.id.imageView);
            itemText = (TextView) view.findViewById(R.id.item_text);
        }

        public void bind(final AllCategoriesResponseDTO.Category category, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(category);
                }
            });

        }
    }
}
