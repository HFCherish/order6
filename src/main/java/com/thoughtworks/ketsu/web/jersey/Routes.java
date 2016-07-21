package com.thoughtworks.ketsu.web.jersey;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }

    public URI productUrl(long productId) {
        return URI.create(baseUri + "products/" + productId);
    }

    public URI userUrl(long userId) {
        return URI.create(baseUri + "users/" + userId);
    }
}
