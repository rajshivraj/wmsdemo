package wmsdemo.repositories.impl.mock;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.google.inject.Singleton;

import wmsdemo.domain.NullProduct;
import wmsdemo.domain.Product;
import wmsdemo.repositories.contract.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ProductMockRepositoryImpl extends GenericMockRepository<Product> implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    public ProductMockRepositoryImpl() {
        this.products = this.createProducts();
    }

    public Product getById(int id) {
        for (Product u : this.products) {
            if (u.getId() == id) {
                return u;
            }
        }
        return new NullProduct();
    }

    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Product create(Product product) {
        product.setId(getCurrentMaxId() + 1);
        this.products.add(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        Product byId = this.getById(product.getId());
        byId.setProductCode(product.getProductCode());
        byId.setProductName(product.getProductName());
        return byId;
    }

    @Override
    public void remove(int id) {
        Product byId = this.getById(id);
        this.products.remove(byId);
    }

    @Override
    public int getNumberOfProducts() {
        return this.products.size();
    }

    private List<Product> createProducts() {
        int numberOfProducts = 10;
        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Product();
            product.setId(i + 1);
            product.setProductCode("SKU" + (i + 1));
            product.setProductName("X" + (i + 1));
            this.products.add(product);
        }
        return this.products;
    }

    private int getCurrentMaxId() {
        Ordering<Product> ordering = new Ordering<Product>() {
            @Override
            public int compare(Product left, Product right) {
                return Ints.compare(left.getId(), right.getId());
            }
        };
        return ordering.max(this.products).getId();
    }
}
