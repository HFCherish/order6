package com.thoughtworks.ketsu.web.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderValidator extends NotNullValidator {
    @Override
    public boolean validate(List<String> toValidates, Map<String, Object> info) {
        super.validate(toValidates, info);

        List items = (List)info.get("order_items");
        if( items.size() == 0 )
            throw new IllegalArgumentException("must contains at least one item.");
        for( Object o: items ) {
            Map item = (Map)o;
            super.validate(Arrays.asList("product_id", "quantity"), item);
        }
        return true;
    }
}
