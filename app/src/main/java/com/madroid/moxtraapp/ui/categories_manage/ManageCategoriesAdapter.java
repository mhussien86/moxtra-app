package com.madroid.moxtraapp.ui.categories_manage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.kcode.bottomlib.BottomDialog;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.ui.DataHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omarmakeen on 6/22/17.
 */

public class ManageCategoriesAdapter extends RecyclerView.Adapter<ManageCategoriesAdapter.MyViewHolder> {


    private final Context mContext;
    private List<AllCategoriesResponseDTO.Category> categories;
    private AllCategoriesResponseDTO.Category selectedCategory = new AllCategoriesResponseDTO().new Category();
    private String newCategoryName;
    private final ManageCategoriesFragment manageCategoriesFragment;

    public ManageCategoriesAdapter(List<AllCategoriesResponseDTO.Category> categories, Context context, ManageCategoriesFragment manageCategoriesFragment) {
        this.categories = categories;
        this.mContext = context;
        this.manageCategoriesFragment = manageCategoriesFragment;

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
                selectedCategory = category;
                showManageCategoryPopupActions();
            }


        });

    }

    private void showManageCategoryPopupActions() {
        BottomDialog dialog = BottomDialog.newInstance("Select your action?", "Cancel", new String[]{"Rename", "Delete"});
        dialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "dialog");
        //add item click listener
        dialog.setListener(new BottomDialog.OnClickListener() {
            @Override
            public void click(int position) {

                if (position == 0)
                    showRenameDialog();
                else
                    showConfirmationDialog();

            }
        });
    }

    private void showRenameDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.categories_rename_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        editText.setText(selectedCategory.getName());
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newCategoryName = editText.getText().toString();
                        if (newCategoryName != null && !newCategoryName.isEmpty()) {
                            JsonObject category = new JsonObject();
                            category.addProperty("id", selectedCategory.getId());
                            category.addProperty("name", newCategoryName);
                            manageCategoriesFragment.manageCategoriesPresenter.renameCategory(DataHolder.getInstance().getToken(), category);
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void showConfirmationDialog() {

        new AlertDialog.Builder(mContext)
                .setTitle(null)
                .setMessage("Delete Category: " + selectedCategory.getName() + "?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        manageCategoriesFragment.manageCategoriesPresenter.deleteCategory(selectedCategory.getId(), DataHolder.getInstance().getToken());
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
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

    public String getNewCategoryName() {
        return newCategoryName;
    }
}

