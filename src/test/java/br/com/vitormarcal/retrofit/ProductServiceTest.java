package br.com.vitormarcal.retrofit;

import org.junit.Test;

public class ProductServiceTest {
    private static final StoreApi storeApi = RestClientMock.getClient("https://wwww.mockproduct.com.br");

    @Test
    public void printProductOf() {
        ProductService productService = new ProductService(storeApi);
        productService.printProductOf(1L);
        // algum teste para validar a impressão
    }

    @Test(expected = RuntimeException.class)
    public void dadoIdDeProdutoInexisten_DeveLancarExcessao() {
        ProductService productService = new ProductService(storeApi);
        productService.printProductOf(2L);
    }
}
