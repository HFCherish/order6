package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.Payment;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentOperationTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private Order order;

    @Before
    public void setUp() {
        order = prepareOrder(prepareProduct(productRepository), prepareUser(userRepository));
    }

    @Test
    public void should_pay_and_get_payment() {
        Map info = paymentJsonForTest();

        order.pay(info);
        Optional<Payment> fetched = order.getPayment();

        assertThat(fetched.isPresent(), is(true));

    }
}
