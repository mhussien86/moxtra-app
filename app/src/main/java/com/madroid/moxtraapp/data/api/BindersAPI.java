package com.madroid.moxtraapp.data.api;

import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
/**
 * Created by mohamed on 13/06/17.
 */

public interface BindersAPI {

    @GET("/me/binders")
    Observable<BindersResponseDTO> getAllBinders(@Query("access_token") String access_token,@Query("filter") String filter,@Query("sort") String sort);
}
