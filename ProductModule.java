package wmsdemo.infrastructure;

import com.google.inject.AbstractModule;

import wmsdemo.repositories.contract.DummyRepository;
import wmsdemo.repositories.contract.ProductRepository;
import wmsdemo.repositories.impl.mock.DummyMockRepositoryImpl;
import wmsdemo.repositories.impl.mock.ProductMockRepositoryImpl;
import wmsdemo.service.contract.DummyService;
import wmsdemo.service.contract.ProductService;
import wmsdemo.service.impl.DummyServiceImpl;
import wmsdemo.service.impl.ProductServiceImpl;

public class ProductModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DummyRepository.class).to(DummyMockRepositoryImpl.class);
        bind(DummyService.class).to(DummyServiceImpl.class);

        bind(ProductRepository.class).to(ProductMockRepositoryImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
    }
}
