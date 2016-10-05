package wmsdemo.web.rest;

import com.google.inject.Inject;

import wmsdemo.domain.Product;
import wmsdemo.service.contract.DummyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dummy")
public class DummyRestService {
    private final DummyService dummyService;

    @Inject
    public DummyRestService(DummyService DummyService) {
        this.dummyService = DummyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getDefaultDummyInJSON() {
        return dummyService.getDefaultProduct();
    }
}
