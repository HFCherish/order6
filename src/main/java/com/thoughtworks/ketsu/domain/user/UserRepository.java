package com.thoughtworks.ketsu.domain.user;

import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    void save(Map userInfo);

    Optional<User> findById(long userId);
}
