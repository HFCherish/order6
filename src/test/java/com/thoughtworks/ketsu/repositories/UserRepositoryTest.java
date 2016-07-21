package com.thoughtworks.ketsu.repositories;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_get_user() {
        Map info = userJsonForTest(USER_NAME);

        userRepository.save(info);
        Optional<User> fetched = userRepository.findById(789l);

        assertThat(fetched.isPresent(), is(true));
    }


}
