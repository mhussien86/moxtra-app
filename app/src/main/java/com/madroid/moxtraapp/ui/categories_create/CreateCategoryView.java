package com.madroid.moxtraapp.ui.categories_create;

import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

public interface CreateCategoryView {

    void setCreateCategory(SimpleResponseDTO responseDTO);

    void showProgress();

    void hideProgress();

    void showError(String message);
}
