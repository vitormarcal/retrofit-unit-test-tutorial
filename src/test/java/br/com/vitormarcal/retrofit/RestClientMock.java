package br.com.vitormarcal.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RestClientMock {
    private static StoreApi storeApi;

    static StoreApi getClient() {
        if (storeApi == null) {
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new FakeInterceptor())
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://wwww.storemock-api.com.br")
                    .client(client)
                    .build();

            storeApi = retrofit.create(StoreApi.class);
        }
        return storeApi;
    }
}
