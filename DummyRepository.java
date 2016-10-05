package wmsdemo.repositories.contract;

import wmsdemo.domain.Product;

public interface DummyRepository extends Repository<Product> {
    Product getDefaultProduct();
}
