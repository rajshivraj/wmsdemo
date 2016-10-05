package wmsdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import wmsdemo.domain.Product;
import wmsdemo.repositories.contract.DummyRepository;
import wmsdemo.service.contract.DummyService;

@Singleton
public class DummyServiceImpl implements DummyService {

    private final DummyRepository dummyRepository;

    @Inject
    public DummyServiceImpl(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    @Override
    public Product getDefaultProduct() {
        Object defaultProduct = this.dummyRepository.getDefaultProduct();
        return this.dummyRepository.getDefaultProduct();
    }

}
