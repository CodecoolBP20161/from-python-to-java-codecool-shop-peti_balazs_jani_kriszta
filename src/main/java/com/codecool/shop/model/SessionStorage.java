package com.codecool.shop.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by csyk on 2016.11.09..
 */
public class SessionStorage {

    public void SessionStorage(HttpServletRequest request, HttpServletResponse response, int id)
            throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("Cart");
        if (cart == null)
        {
            cart = new ShoppingCart ();
            session.setAttribute("Cart", cart);
        }

        cart.addToCart(id);

    }

}
