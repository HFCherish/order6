package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;

import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void save(Map userInfo) {

    }

    @Override
    public Optional<User> findById(long userId) {
        return Optional.ofNullable(new User());
    }
}
