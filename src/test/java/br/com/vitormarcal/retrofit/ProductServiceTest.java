package br.com.vitormarcal.retrofit;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {
    private static final StoreApi storeApi = RestClientMock.getClient();

    @Test
    public void printProductOfId() {
        ProductService productService = new ProductService(storeApi);
        productService.printProductOfId(1L);
        // some test to validate the print
    }

    @Test(expected = RuntimeException.class)
    public void giveProductNotFound_ThenThrowRuntimeException() {
        ProductService productService = new ProductService(storeApi);
        productService.printProductOfId(2L);
    }

    @Test
    public void productIsPresent() {
        ProductService productService = new ProductService(storeApi);
        Optional<Product> optionalProduct = productService.findInStoreApi(1L);
        assertTrue(optionalProduct.isPresent());
    }

    @Test
    public void productIsNotPresent() {
        ProductService productService = new ProductService(storeApi);
        Optional<Product> optionalProduct = productService.findInStoreApi(2L);
        assertFalse(optionalProduct.isPresent());
    }
}
