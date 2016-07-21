package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.prepareProduct;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {
    private String productBaseUrl = "/products";

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_product() {
        Response response = post(productBaseUrl, productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_product_incomplete() {
        Map prodInfo = productJsonForTest();

        //name empty
        prodInfo.remove("name");

        Response response = post(productBaseUrl, prodInfo);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_one_product_successfully() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productBaseUrl + "/" + product.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map prodInfo = response.readEntity(Map.class);
        verifyProductResponseInfo(product, prodInfo);

    }

    private void verifyProductResponseInfo(Product product, Map prodInfo) {
        assertThat(prodInfo.get("uri"), is(productBaseUrl + "/" + product.getId()));
        assertThat(prodInfo.get("name"), is(product.getName()));
        assertThat(prodInfo.get("description"), is(product.getDescription()));
        assertThat((double)prodInfo.get("price"), is(product.getPrice()));
    }

    @Test
    public void should_404_when_get_one_product_given_invalid_id() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productBaseUrl + "/1" + product.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));

    }

    @Test
    public void should_get_all_products_succssfully() {
        Product product = prepareProduct(productRepository);

        Response response = get(productBaseUrl);

        assertThat(response.getStatus(), is(200));
        List prodsInfo = response.readEntity(List.class);
        assertThat(prodsInfo.size(), is(1));
        verifyProductResponseInfo(product, (Map)prodsInfo.get(0));
    }
}
