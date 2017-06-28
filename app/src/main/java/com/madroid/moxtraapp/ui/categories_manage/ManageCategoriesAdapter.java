package com.madroid.moxtraapp.ui.categories_manage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kcode.bottomlib.BottomDialog;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.ui.categories_binders_add.BindersAddActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omarmakeen on 6/22/17.
 */

public class ManageCategoriesAdapter extends RecyclerView.Adapter<ManageCategoriesAdapter.MyViewHolder> {


    private final Context mContext;
    private final List<AllCategoriesResponseDTO.Category> categories;
    private final AllCategoriesResponseDTO.Category selectedCategory = new AllCategoriesResponseDTO().new Category();

    public ManageCategoriesAdapter(List<AllCategoriesResponseDTO.Category> categories, Context context) {
        this.categories = categories;
        this.mContext = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_categories_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final AllCategoriesResponseDTO.Category category = categories.get(position);
        holder.categoryName.setText(category.getName());
        holder.categoryNameItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showManageCategoryPopupActions();
            }


        });

    }

    private void showManageCategoryPopupActions() {
        BottomDialog dialog = BottomDialog.newInstance("Select", "Cancel", new String[]{"Rename", "Delete"});
        dialog.show(((AppCompatActivity)mContext).getSupportFragmentManager(), "dialog");
        //add item click listener
        dialog.setListener(new BottomDialog.OnClickListener() {
            @Override
            public void click(int position) {
                Toast.makeText(mContext, "" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openAddBindersActivity() {
        Intent intent = new Intent(mContext, BindersAddActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return (categories != null) ? categories.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.category_name_item)
        public View categoryNameItem;
        @BindView(R.id.category_name)
        public TextView categoryName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public AllCategoriesResponseDTO.Category getSelectedCategory() {
        return selectedCategory;
    }
}

