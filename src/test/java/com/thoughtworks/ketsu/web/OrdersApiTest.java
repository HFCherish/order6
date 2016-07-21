package com.thoughtworks.ketsu.web;

import com.google.common.collect.Ordering;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.Order;
import com.thoughtworks.ketsu.domain.user.OrderItem;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrdersApiTest extends ApiSupport {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product;
    private String orderBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
        orderBaseUrl = "/users/" + user.getId() + "/orders";
    }

    @Test
    public void should_create_order_success() {
        Response response = post(orderBaseUrl, orderJsonForTest(product.getId()));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(orderBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));

    }

    @Test
    public void should_400_when_create_order_given_incomplete_base_info() {
        Map info = orderJsonForTest(product.getId());
        //name empty
        info.remove("name");

        Response response = post(orderBaseUrl, info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_400_when_create_order_given_incomplete_items_info() {
        Map info = orderJsonForTest(product.getId());
        //name empty
        info.remove("order_items");

        Response response = post(orderBaseUrl, info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_400_when_create_order_given_invalid_items_id() {
        Map info = orderJsonForTest(product.getId() + 1);

        Response response = post(orderBaseUrl, info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_order_success() {
        Order order = prepareOrder(product, user);
        String getOneUrl = orderBaseUrl + "/" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map orderInfo = response.readEntity(Map.class);
        assertThat(orderInfo.get("uri"), is(getOneUrl));
        assertThat(orderInfo.get("name"), is(order.getName()));
        assertThat(orderInfo.get("address"), is(order.getAddress()));
        assertThat(orderInfo.get("phone"), is(order.getPhone()));
        assertThat((double)orderInfo.get("total_price"), is(order.getTotalPrice()));
        assertThat(new DateTime(orderInfo.get("created_at")), is(order.getCreatedAt()));

        List items = (List)orderInfo.get("order_items");
        assertThat(items.size(), is(1));
        Map itemInfo = (Map)items.get(0);
        OrderItem item = order.getOrderItems().get(0);
        assertThat(Long.valueOf(itemInfo.get("product_id").toString()), is(item.getProductId()));
        assertThat(itemInfo.get("quantity"), is(item.getQuantity()));
        assertThat((double)itemInfo.get("amount"), is(item.getAmount()));
    }
}
