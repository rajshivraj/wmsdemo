package wmsdemo.repositories.impl.mock;

import wmsdemo.domain.Product;
import wmsdemo.repositories.contract.DummyRepository;

public class DummyMockRepositoryImpl extends GenericMockRepository<Product> implements DummyRepository {

    @Override
    public Product getDefaultProduct() {
        Product product = new Product();

        product.setProductCode("JonFromREST");
        product.setProductName("DoeFromREST");
        return product;
    }
}
