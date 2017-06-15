package com.madroid.moxtraapp.dtos.catergoriesandbinders;

import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

/**
 * Created by omarmakeen on 6/15/17.
 */

public class CategoriesAndBindersDTO {

    public CategoriesAndBindersDTO(AllCategoriesResponseDTO allCategoriesResponseDTO, BindersResponseDTO bindersResponseDTO) {
        this.allCategoriesResponseDTO = allCategoriesResponseDTO;
        this.bindersResponseDTO = bindersResponseDTO;
    }

    public AllCategoriesResponseDTO allCategoriesResponseDTO;
    public BindersResponseDTO bindersResponseDTO;
}
