package com.madroid.moxtraapp.interactors.categories;

import com.google.gson.JsonObject;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.dtos.categories.BindersAddCategoryRequestDTO;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

/**
 * Created by mohamed on 25/03/17.
 */

public interface CategoriesInteractor {

    void getAllCategories(String accecss_tokes, OnGetAllCategoriesFinished onGetAllCategoriesFinished);

    void assignBindersToCategory(Integer categoryId, String accecss_tokes, BindersAddCategoryRequestDTO body, OnAssignBindersToCategoryFinished onAssignBindersToCategoryFinished);

    void createCategory(String accecss_tokes, JsonObject category_name, OnCreateCategoryFinished onCreateCategoryFinished);

    void renameCategory(String accecss_tokes, JsonObject category, OnRenameCategoryFinished onRenameCategoryFinished);

    void deleteCategory(Integer categoryId, String accecss_tokes, OnDeleteCategoryFinished onDeleteCategoryFinished);

    interface OnGetAllCategoriesFinished {

        void onGetAllCategoriesSucceed(AllCategoriesResponseDTO allCategoriesResponseDTO);

        void onGetAllCategoriesFailed(String message);
    }

    interface OnAssignBindersToCategoryFinished {

        void onAssignBindersToCategorySucceed(SimpleResponseDTO response);

        void onAssignBindersToCategoryFailed(String message);
    }

    interface OnCreateCategoryFinished {

        void onCreateCategorySucceed(SimpleResponseDTO response);

        void onCreateCategoryFailed(String message);
    }

    interface OnRenameCategoryFinished {

        void onRenameCategorySucceed(SimpleResponseDTO response);

        void onRenameCategoryFailed(String message);
    }

    interface OnDeleteCategoryFinished {

        void onDeleteCategorySucceed(SimpleResponseDTO response);

        void onDeleteCategoryFailed(String message);
    }

    void unsubscribe();

}
