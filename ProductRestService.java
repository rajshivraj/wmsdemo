package wmsdemo.web.rest;

import com.google.inject.Inject;

import wmsdemo.domain.Product;
import wmsdemo.service.contract.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductRestService {

    private final ProductService productService;

    @Inject
    public ProductRestService(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Path("numberOfProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public int getNumberOfProducts() {
        return productService.getNumberOfProducts();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductsInJSON() {
        return productService.getAllProducts();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") int id) {
        return productService.getById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product create(Product product) {
        return productService.createNewProduct(product);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product update(Product product) {
        return productService.update(product);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("id") int id) {
        productService.remove(id);
    }
}
