package wmsdemo.service.contract;

import java.util.List;

import wmsdemo.domain.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product getById(int id);

    Product createNewProduct(Product Product);

    Product update(Product Product);

    void remove(int id);

    int getNumberOfProducts();
}
