package com.thoughtworks.ketsu.support;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("description", "teahcer");
            put("price", 12.1);
        }};
    }
}
