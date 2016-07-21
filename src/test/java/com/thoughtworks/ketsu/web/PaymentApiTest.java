package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.Payment;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private Order order;
    private String paymentBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        order = prepareOrder(prepareProduct(productRepository), prepareUser(userRepository));
        paymentBaseUrl = "users/" + order.getUserId() + "/orders/" + order.getId() + "/payment";
    }

    @Test
    public void should_pay() {
        Response response = post(paymentBaseUrl, paymentJsonForTest());

        assertThat(response.getStatus(), is(201));

    }

    @Test
    public void should_400_when_pay_given_incomplete_input() {
        Map<String, Object> info = paymentJsonForTest();
        //type empty
        info.remove("pay_type");

        Response response = post(paymentBaseUrl, info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_payment() {
        Map<String, Object> info = paymentJsonForTest();
        order.pay(info);
        Payment payment = order.getPayment().get();

        Response response = get(paymentBaseUrl);

        assertThat(response.getStatus(), is(200));
        Map fetchedInfo = response.readEntity(Map.class);
        assertThat(fetchedInfo.get("pay_type"), is(info.get("pay_type")));
        assertThat(fetchedInfo.get("amount"), is(info.get("amount")));
        assertThat(fetchedInfo.get("order_uri"), is("users/" + order.getUserId() + "/orders/" + order.getId()));
        assertThat(fetchedInfo.get("uri"), is(paymentBaseUrl));
        assertThat(new DateTime(fetchedInfo.get("created_at")), is(payment.getCreatedAt()));
    }
}
