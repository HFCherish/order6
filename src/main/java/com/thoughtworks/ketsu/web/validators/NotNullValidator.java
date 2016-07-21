package com.thoughtworks.ketsu.web.validators;

import java.util.List;
import java.util.Map;

public class NotNullValidator {

    public boolean validate(List<String> toValidates, Map<String, Object> info) {
        for(String toValidate: toValidates) {
            if(info.get(toValidate) == null) {
                throw new IllegalArgumentException("must contain " + toValidate + " field.");
            }
        }
        return true;
    }
}
