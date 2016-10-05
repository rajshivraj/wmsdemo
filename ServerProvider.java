package wmsdemo.tests.integration.web.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;

import wmsdemo.repositories.contract.DummyRepository;
import wmsdemo.repositories.contract.ProductRepository;
import wmsdemo.repositories.impl.mock.DummyMockRepositoryImpl;
import wmsdemo.repositories.impl.mock.ProductMockRepositoryImpl;
import wmsdemo.service.contract.DummyService;
import wmsdemo.service.contract.ProductService;
import wmsdemo.service.impl.DummyServiceImpl;
import wmsdemo.service.impl.ProductServiceImpl;
import wmsdemo.repositories.contract.ProductRepository;
import wmsdemo.repositories.impl.mock.DummyMockRepositoryImpl;
import wmsdemo.repositories.impl.mock.ProductMockRepositoryImpl;
import wmsdemo.service.contract.DummyService;
import wmsdemo.service.contract.ProductService;
import wmsdemo.service.impl.DummyServiceImpl;
import wmsdemo.service.impl.ProductServiceImpl;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class ServerProvider {

    private final URI BASE_URI = getBaseURI();
    private HttpServer server;

    protected URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

    public void createServer() throws IOException {
        System.out.println("Starting grizzly...");

        Injector injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(ProductService.class).to(ProductServiceImpl.class);
                bind(ProductRepository.class).to(ProductMockRepositoryImpl.class);
                bind(DummyService.class).to(DummyServiceImpl.class);
                bind(DummyRepository.class).to(DummyMockRepositoryImpl.class);

                // hook Jackson into Jersey as the POJO <-> JSON mapper
                bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
            }
        });

        
        ResourceConfig rc = new PackagesResourceConfig("wmsdemo.web");
        IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
        server = GrizzlyServerFactory.createHttpServer(BASE_URI + "web/", rc, ioc);

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%srest/application.wadl\nTry out %sWMS-demo\nHit enter to stop it...",
                BASE_URI, BASE_URI));
    }

    public void stop() {
        server.stop();
    }
}
