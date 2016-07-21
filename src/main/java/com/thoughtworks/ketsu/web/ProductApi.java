package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;
import com.thoughtworks.ketsu.web.validators.NotNullValidator;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Path("products")
public class ProductApi {
    @Inject
    ProductRepository productRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map prodInfo,
                           @Context Routes routes) {
        new NotNullValidator().validate(Arrays.asList("name", "description", "price"), prodInfo);
        productRepository.save(prodInfo);

        return Response.created(routes.productUrl(Long.valueOf(prodInfo.get("id").toString()))).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("id") long id) {
        return productRepository.findById(id)
                .map(product -> product)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return new ArrayList();
    }
}
