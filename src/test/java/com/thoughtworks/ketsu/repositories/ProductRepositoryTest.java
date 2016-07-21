package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_get_a_product() {
        Map info = productJsonForTest();

        productRepository.save(info);
        Long id = Long.valueOf(info.get("id").toString());
        Optional<Product> fetched = productRepository.findById(id);

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(id));
    }


}
