package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    void save(@Param("info") Map orderInfo, @Param("userId") long userId);

    Order findById(@Param("id") long id);

    List<Order> findAll(@Param("userId") long userId);
}
