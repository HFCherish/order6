package com.thoughtworks.ketsu.domain.product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    void save(Map prodInfo);

    Optional<Product> findById(long id);

    List<Product> findAll();
}
