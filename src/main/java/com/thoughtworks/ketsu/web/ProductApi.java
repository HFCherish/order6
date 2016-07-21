package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.web.jersey.Routes;
import com.thoughtworks.ketsu.web.validators.NotNullValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Map;

@Path("products")
public class ProductApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map prodInfo,
                           @Context Routes routes) {
        new NotNullValidator().validate(Arrays.asList("name", "description", "price"), prodInfo);
        return Response.created(routes.productUrl(798l)).build();
    }
}
