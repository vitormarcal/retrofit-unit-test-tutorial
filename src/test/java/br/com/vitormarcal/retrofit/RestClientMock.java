package br.com.vitormarcal.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClientMock {
    private static StoreApi storeApi;

    public static StoreApi getClient(String endpoint) {
        if (storeApi == null) {
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new FakeInterceptor())
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endpoint)
                    .client(client)
                    .build();

            storeApi = retrofit.create(StoreApi.class);
        }
        return storeApi;
    }
}
