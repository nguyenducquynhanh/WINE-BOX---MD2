package caseMD2.services;

import caseMD2.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();

    void add(Order newOrder);

    void update();

    Order getOrderById(int id);

    boolean exist(int id);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateId(int id);

    void remove(Order order);
}
