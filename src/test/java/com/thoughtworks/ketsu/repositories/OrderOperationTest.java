package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderOperationTest {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product;

    @Before
    public void setUp() {
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
    }

    @Test
    public void should_save_and_get_order() {
        Map info = orderJsonForTest(product.getId());

        user.placeOrder(info);
        Long id = Long.valueOf(info.get("id").toString());
        Optional<Order> fethced = user.getOrderById(id);

        assertThat(fethced.isPresent(), is(true));
        assertThat(fethced.get().getId(), is(id));

    }

    @Test
    public void should_get_all_orders() {
        Order order = prepareOrder(product, user);

        List<Order> fetched = user.getAllOrders();

        assertThat(fetched.size(), is(1));
        assertThat(fetched.get(0).getId(), is(order.getId()));
    }
}
