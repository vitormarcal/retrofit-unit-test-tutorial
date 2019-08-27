package br.com.vitormarcal.retrofit;

import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductService {
    private final StoreApi storeApi;
    private static final Logger log = Logger.getLogger(ProductService.class.getName());

    public ProductService(StoreApi storeApi) {
        this.storeApi = storeApi;
    }

    public void printProductOf(Long id) {
        Optional<Product> optionalProduct = findInStoreApi(id);
        if (optionalProduct.isPresent()) {
            print(optionalProduct.get());
        } else {
            throw new RuntimeException("Produto não existe");
        }
    }

    private Optional<Product> findInStoreApi(Long id) {
        final Response<Product> response;
        try {
            response = this.storeApi.getDetail(id).execute();
            if (response.isSuccessful()) {
                return Optional.ofNullable(response.body());
            } else {
                log.log(Level.WARNING, "O corpo da resposta veio vazia ao chamar products com " + id);
                return Optional.empty();
            }
        } catch (IOException e) {
            log.log(Level.WARNING, "Erro com id " + id, e);
            return Optional.empty();
        }

    }

    private void print(Product product) {
        System.out.println(product);
    }
}
