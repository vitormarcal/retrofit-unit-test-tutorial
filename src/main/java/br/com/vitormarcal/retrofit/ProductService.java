package br.com.vitormarcal.retrofit;

import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


class ProductService {
    private final StoreApi storeApi;
    private static final Logger log = Logger.getLogger(ProductService.class.getName());

    ProductService(StoreApi storeApi) {
        this.storeApi = storeApi;
    }

    void printProductOfId(Long id) {
        Optional<Product> optionalProduct = findInStoreApi(id);
        if (optionalProduct.isPresent()) {
            print(optionalProduct.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    Optional<Product> findInStoreApi(Long id) {
        final Response<Product> response;
        try {
            response = this.storeApi.getDetail(id).execute();
            if (response.isSuccessful()) {
                return Optional.ofNullable(response.body());
            } else {
                log.log(Level.WARNING, "The response body is empty with id " + id);
                return Optional.empty();
            }
        } catch (IOException e) {
            log.log(Level.WARNING, "Error with id " + id, e);
            return Optional.empty();
        }

    }

    private void print(Product product) {
        System.out.println(product);
    }
}
