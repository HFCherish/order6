package com.thoughtworks.ketsu.web.validators;

import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderValidator extends NotNullValidator {
    @Inject
    ProductRepository productRepository;

    @Override
    public boolean validate(List<String> toValidates, Map<String, Object> info) {
        super.validate(toValidates, info);

        List items = (List)info.get("order_items");
        if( items.size() == 0 )
            throw new IllegalArgumentException("must contains at least one item.");
        for( Object o: items ) {
            Map item = (Map)o;
            super.validate(Arrays.asList("product_id", "quantity"), item);
            if(!productRepository.findById(Long.valueOf(item.get("product_id").toString())).isPresent())
                throw new IllegalArgumentException("product id is invalid.");
        }
        return true;
    }
}
