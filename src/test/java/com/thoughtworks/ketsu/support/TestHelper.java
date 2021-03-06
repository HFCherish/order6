package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.Payment;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final String USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = ";'KPK";

    public static Map<String, Object> paymentJsonForTest() {
        return new HashMap<String, Object>() {{
            put("pay_type", "CASH");
            put("amount", 678.9);
        }};
    }

    public static Payment preparePayment(Order order) {
        Map<String, Object> info = paymentJsonForTest();
        order.pay(info);
        return order.getPayment().get();
    }

    public static Map<String, Object> orderJsonForTest(long prodId) {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("address", "beijing");
            put("phone", "7689");
            put("order_items", Arrays.asList(new HashMap() {{
                put("product_id", prodId);
                put("quantity", 2);
            }}));
        }};
    }

    public static Order prepareOrder(Product product, User user) {
        Map info = orderJsonForTest(product.getId());
        user.placeOrder(info);
        return user.getOrderById(Long.valueOf(info.get("id").toString())).get();
    }

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static User prepareUser(UserRepository userRepository) {
        Map info = userJsonForTest(USER_NAME);
        userRepository.save(info);
        return userRepository.findById(Long.valueOf(info.get("id").toString())).get();
    }

    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("description", "teahcer");
            put("price", 12.1);
        }};
    }

    public static Product prepareProduct(ProductRepository productRepository) {
        Map info = productJsonForTest();
        productRepository.save(info);
        return productRepository.findById(Long.valueOf(info.get("id").toString())).get();
    }
}
