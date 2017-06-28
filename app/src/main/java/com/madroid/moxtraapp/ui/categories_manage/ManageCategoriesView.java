package com.madroid.moxtraapp.ui.categories_manage;

import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

public interface ManageCategoriesView {

    void setRenameCategory(SimpleResponseDTO responseDTO);

    void setDeleteCategory(SimpleResponseDTO responseDTO);

    void showProgress();

    void hideProgress();

    void showError(String message);
}
