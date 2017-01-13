package com.madroid.moxtraapp.data;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed on 18/12/16.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://95.177.208.232/moxtra-webservice/";

    OkHttpClient.Builder httpClient;
    Retrofit.Builder builder;

    public ServiceGenerator(){
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }


    // method to create any retrofit service
    public  <T> T createService(Class<T> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());
                requestBuilder.addHeader("Content-Type", "application/json; text/html; charset=UTF-8");

                Request request = requestBuilder.build();



                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);
        httpClient.retryOnConnectionFailure(true);
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }


    public  <T> T createServiceWithRequestBody(Class<T> serviceClass, final String value) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "%5B%5D="+value);

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());
                requestBuilder.addHeader("Content-Type", "application/json; text/html; charset=UTF-8");
                requestBuilder.post(body);

                Request request = requestBuilder.build();




                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);
        httpClient.retryOnConnectionFailure(true);
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
