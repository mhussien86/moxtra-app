package com.madroid.moxtraapp.data.api;

import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.dtos.categories.BindersAddCategoryRequestDTO;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mohamed on 24/03/17.
 */

public interface CategoriesAPI {


    @GET("/me/categories?")
    Observable<AllCategoriesResponseDTO> getAllCategories(@Query("access_token") String access_token);

    @POST("/me/categories/{categoryId}")
    Observable<SimpleResponseDTO> assignBindersToCategory(@Path("categoryId") Integer categoryId, @Query("access_token") String access_token,@Body BindersAddCategoryRequestDTO body);
}
