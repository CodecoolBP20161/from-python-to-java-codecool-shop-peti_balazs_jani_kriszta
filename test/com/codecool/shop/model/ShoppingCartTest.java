package com.codecool.shop.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    @Mock
    ShoppingCart mockShoppingCart;

    private ShoppingCart shoppingCart;
    private LineItem lineitem;

    @Before
    public void setUp() throws Exception {
        PopulateData.populateData("MEM");
        lineitem = new LineItem(1, "MEM");
        shoppingCart = new ShoppingCart();
    }

    @After
    public void tearDown() throws Exception {
        shoppingCart = null;
        lineitem = null;
    }

    @Test
    public void addToCart() throws Exception {

    }

    @Test
    public void getTotalQuantity() throws Exception {
        assertEquals(0, shoppingCart.getTotalQuantity());
    }

    @Test
    public void getTotalPrice() throws Exception {

    }

    @Test
    public void getAllLineItems() throws Exception {

    }

    @Test
    public void removeLineItem() throws Exception {

    }

}