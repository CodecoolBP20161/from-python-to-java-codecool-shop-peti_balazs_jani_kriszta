package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.OrderDao;
import java.util.ArrayList;

/**
 * Created by krisztinabaranyai on 09/11/2016.
 */
public class OrderDaoMem implements OrderDao {
    private static List<Order> DATA = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Order order) {
        order.setId(DATA.size()+1);
        DATA.add(order);
    }

    @Override
    public Order find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Order> getAll() {
        return DATA;
    }

}
