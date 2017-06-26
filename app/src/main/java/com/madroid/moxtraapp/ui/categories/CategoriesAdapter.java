package com.madroid.moxtraapp.ui.categories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.ui.MainActivity;
import com.madroid.moxtraapp.ui.categories_binders_add.BindersAddActivity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Omar Makeen on 25/03/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{


    private final Context mContext;
    private final List<AllCategoriesResponseDTO.Category> categories;
    private final List<BindersResponseDTO.Binder> binders;
    Map<Integer, List<BindersResponseDTO.Binder>> categoriesBindersMap;
    private CategoriesFragment categoriesFragment;

    public CategoriesAdapter(List<AllCategoriesResponseDTO.Category> categories, Map<Integer, List<BindersResponseDTO.Binder>> categoriesBindersMap, List<BindersResponseDTO.Binder> binders, Context context, CategoriesFragment categoriesFragment) {
        this.categories = categories;
        this.binders = binders;
        this.categoriesBindersMap = categoriesBindersMap;
        this.mContext = context;
        this.categoriesFragment = categoriesFragment;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final AllCategoriesResponseDTO.Category category = categories.get(position);
        holder.itemText.setText(category.getName());

        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(mContext,4);
        holder.bindersRecycleView.setLayoutManager(mLayoutManager);
        mAdapter = new BindersAdapter(categoriesBindersMap.get(category.getId()), mContext);
        holder.bindersRecycleView.setAdapter(mAdapter);
        holder.addBinders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBindersActivity(category.getId());
            }
        });
    }

    public void openAddBindersActivity(Integer categoryId){
        Intent intent = new Intent(mContext, BindersAddActivity.class);
        intent.putExtra("binders", (Serializable) binders);
        intent.putExtra("category_Id", (int) categoryId);
        categoriesFragment.startActivityForResult(intent,0);
        MainActivity mainActivity = (MainActivity) mContext;
//        mainActivity.overridePendingTransition(R.anim.slidein_from_right, R.anim.slideout_to_left);
    }

    @Override
    public int getItemCount() {
        return (categories!=null)?categories.size():0;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView itemText;
        public RecyclerView bindersRecycleView;
        public View addBinders;

        public MyViewHolder(View view) {
            super(view);
            itemText = (TextView) view.findViewById(R.id.category_name);
            bindersRecycleView = (RecyclerView) view.findViewById(R.id.binders_list);
            addBinders = (View) view.findViewById(R.id.add_binders_holder);
        }

    }
}
