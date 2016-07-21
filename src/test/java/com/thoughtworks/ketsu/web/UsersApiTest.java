package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    private String userBaseUrl = "/users";

    @Inject
    UserRepository userRepository;

    @Test
    public void should_register_successfully() {
        Response response = post(userBaseUrl, userJsonForTest(USER_NAME));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(userBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }


    @Test
    public void should_400_when_register_given_invalid_name() {
        Response response = post(userBaseUrl, userJsonForTest(INVALID_USER_NAME));

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_one_user_successfully() {
        User user = prepareUser(userRepository);
        String getOneUrl = userBaseUrl + "/" + user.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));

    }
}
