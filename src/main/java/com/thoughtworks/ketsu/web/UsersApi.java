package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("users")
public class UsersApi {
    @Context
    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Map userInfo,
                             @Context Routes routes) {
        if (userInfo.get("name") == null || !userInfo.get("name").toString().matches("^[a-zA-Z\\d]+$"))
            throw new IllegalArgumentException("must contains name composed of letters and numbers.");

        userRepository.save(userInfo);
        return Response.created(routes.userUrl(809l)).build();
    }
}
