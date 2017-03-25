package com.madroid.moxtraapp.data.api;

import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mohamed on 24/03/17.
 */

public interface CategoriesAPI {


    @GET("/me/categories?")
    Observable<AllCategoriesResponseDTO> getAllCategories(@Query("access_token") String acccess_token);
}
