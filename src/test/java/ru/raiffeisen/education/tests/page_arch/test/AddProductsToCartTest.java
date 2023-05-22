package ru.raiffeisen.education.tests.page_arch.test;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddProductsToCartTest extends TestBase {

    @Test
    void addThreeRandomProductPositionToCart() {

        this.app.addProductsToCart(3);
        this.app.removeAllProductsFromCart();

        assertThat(this.app.getProductCountInCart())
                .isEqualTo(0);

    }

}
