package wmsdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import wmsdemo.domain.Product;
import wmsdemo.repositories.contract.ProductRepository;
import wmsdemo.service.contract.ProductService;

import java.util.List;

@Singleton
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List getAllProducts() {
        return this.productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return this.productRepository.getById(id);
    }

    @Override
    public Product createNewProduct(Product product) {
        Product u = this.productRepository.create(product);
        return u;
    }

    @Override
    public Product update(Product product) {
        return this.productRepository.update(product);
    }

    @Override
    public void remove(int id) {
        this.productRepository.remove(id);
    }

    @Override
    public int getNumberOfProducts() {
        return this.productRepository.getNumberOfProducts();
    }
}
