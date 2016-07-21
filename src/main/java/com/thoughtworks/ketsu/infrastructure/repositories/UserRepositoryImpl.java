package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Inject
    UserMapper userMapper;

    @Override
    public void save(Map userInfo) {
        userMapper.save(userInfo);
    }

    @Override
    public Optional<User> findById(long userId) {
        return Optional.ofNullable(userMapper.findById(userId));
    }
}
