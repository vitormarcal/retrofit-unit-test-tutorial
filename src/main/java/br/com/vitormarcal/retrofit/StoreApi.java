package br.com.vitormarcal.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoreApi {

    @GET("products/{id}")
    Call<Product> getDetail(@Path("id") Long id);
}
