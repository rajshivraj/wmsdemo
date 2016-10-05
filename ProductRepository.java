package wmsdemo.repositories.contract;

import wmsdemo.domain.Product;

public interface ProductRepository extends Repository<Product> {
    Product create(Product product);

    Product update(Product product);

    void remove(int id);

    int getNumberOfProducts();
}
