package com.codecool.shop.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineItemTest {


    LineItem lineItem;

    @Before
    public void setUp() throws Exception {
        PopulateData.populateData("MEM");
        lineItem = new LineItem(1, "MEM");
    }

    @After
    public void tearDown() throws Exception {
        lineItem = null;
    }

    @Test
    public void setQuantityNoArg() throws Exception {
        lineItem.setQuantity(10);
        lineItem.setQuantity();
        assertEquals(11, lineItem.getQuantity());
    }

    @Test
    public void setQuantityWithArg() throws Exception {
        lineItem.setQuantity(10);
        assertEquals(10, lineItem.getQuantity());
    }

    @Test
    public void getQuantity() throws Exception {
        lineItem.setQuantity(10);
        assertEquals(10, lineItem.getQuantity());
    }

    @Test
    public void setSubtotalNoArg() throws Exception {
        lineItem.setQuantity(2);
        lineItem.setDefaultPrice(100);
        lineItem.setSubtotal();
        assertEquals(200, lineItem.getSubtotal(), 0.0);
    }

    @Test
    public void setSubtotalWithArg() throws Exception {
        lineItem.setSubtotal(200);
        assertEquals(200, lineItem.getSubtotal(), 0.0);
    }

    @Test
    public void getSubtotal() throws Exception {
        lineItem.setSubtotal(100);
        assertEquals(100, lineItem.getSubtotal(), 0.0);
    }

    @Test
    public void setProductName() throws Exception {
        lineItem.setProductName("testName");
        assertEquals("testName", lineItem.getProductName());
    }

    @Test
    public void getProductName() throws Exception {
        lineItem.setProductName("testName");
        assertEquals("testName", lineItem.getProductName());
    }

    @Test
    public void setProductID() throws Exception {
        lineItem.setProductID(15);
        assertEquals(15, lineItem.getProductID());
    }

    @Test
    public void getProductID() throws Exception {
        lineItem.setProductID(15);
        assertEquals(15, lineItem.getProductID());
    }

    @Test
    public void setDefaultPrice() throws Exception {
        lineItem.setDefaultPrice(150);
        assertEquals(150, lineItem.getDefaultPrice(), 0.0);
    }

    @Test
    public void getDefaultPrice() throws Exception {
        lineItem.setDefaultPrice(150);
        assertEquals(150, lineItem.getDefaultPrice(), 0.0);
    }

}