package com.madroid.moxtraapp.ui.binders_add;

import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

public interface BindersAddView {

    void setAssignBindersToCategory(SimpleResponseDTO responseDTO);

    void showProgress();

    void hideProgress();

    void showError(String message);
}
