package caseMD2.services;

import caseMD2.menu.Menu;
import caseMD2.model.Order;
import caseMD2.model.OrderItem;
import caseMD2.model.Product;
import caseMD2.model.User;
import caseMD2.utils.CSVUtils;
import caseMD2.view.OrderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderItemService implements IOrderItemServices{
    public static ArrayList<OrderItem> listOrderItem = new ArrayList<>();

    public static ArrayList<OrderItem> listOrderPaid = new ArrayList<>();
    private static final UserService userServices = new UserService();
    private static final OrderView orderView = new OrderView();
    private static final String fileName = "F:\\MD2\\CaseMD2\\data\\orderItem.csv";
    private static final String path = "F:\\MD2\\CaseMD2\\data\\orderPaid.csv";



    @Override
    public List<OrderItem> getOrderItems() {
        if (listOrderItem.size() == 0) {
            List<String> records = CSVUtils.read(fileName);

            for (String record : records) {
                listOrderItem.add(new OrderItem(record));
            }
            return listOrderItem;
        }
        return null;
    }

    public static List<OrderItem> getOrdersPaid() {
        if (listOrderPaid.size() == 0) {
            List<String> records = CSVUtils.read(path);

            for (String record : records) {
                listOrderPaid.add(new OrderItem(record));
            }
            return listOrderPaid;
        }
        return null;
    }

    public static void addOrderPaid(OrderItem newOrder) {
        listOrderPaid.add(newOrder);
        updateOrderListPaid();
    }

    public static void updateOrderListPaid() {
        CSVUtils.write(path, listOrderPaid);
    }

    @Override
    public void add(OrderItem newOrderItem) {
        listOrderItem.add(newOrderItem);
        CSVUtils.write(fileName, listOrderItem);
    }

    @Override
    public void update() {
        CSVUtils.write(fileName, listOrderItem);
    }

    @Override
    public OrderItem getOrderItemById(long id) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getOrderId() == id) {
                return orderItem;
            }
        }
        return null;
    }

    public OrderItem getOrderItemByName(String name) {
        for (OrderItem orderItem : listOrderItem) {
            if (Objects.equals(orderItem.getName(), name)) {
                return orderItem;
            }
        }
        return null;
    }

    public List<OrderItem> getListOrderItemByName(String name) {
        List<OrderItem> list = new ArrayList<>();
        for (OrderItem orderItem : listOrderItem) {
            if (Objects.equals(orderItem.getName(), name)) {
                list.add(orderItem);
            }
        }
        return list;
    }

    public boolean isExistProduct(Product product) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getProductId() == product.getId()) {
                return true;
            }
        }
        return false;
    }

    public void resetListOrderItem(String name) {
        listOrderItem.removeIf(orderItem -> (orderItem.getName().equals(name)));
        CSVUtils.write(fileName, listOrderItem);
    }

    public static boolean isExistNameOfOrderItemList(String name) {
        for(OrderItem orderItem : listOrderItem){
            if(orderItem.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkNameOnList(String name) {
        for (OrderItem orderItem : listOrderItem) {
            if (orderItem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void showListOrder() {
        User user = userServices.getUserById(Menu.userId);
        if (!checkNameOnList(user.getName())) {
            System.out.println("----------  Danh sách sản phẩm đặt hàng trống!  ----------");
            System.out.println(" ");
        }
                int totalMoney = 0;
                System.out.println("------------------------------------------------- DANH SÁCH ĐẶT HÀNG CỦA BẠN -------------------------------------------------");
                System.out.printf("*             %-15s %-18s %-12s %-15s %-20s %-16s *\n", "ID", "Tên sản phẩm", "Giá", "Số lượng", "Tổng tiền", "Thời gian mua");

                for (OrderItem orderItem : listOrderItem) {
                    if (user.getName().equals(orderItem.getName())) {
                        totalMoney += orderItem.getTotal();
                        System.out.printf("*        %-16s %-20s %-15s  %-13s %-20s %-16s *\n", orderItem.getId(),
                                orderItem.getProductName(), ProductService.addCharacters((int) orderItem.getPrice()),
                                orderItem.getQuantity(), ProductService.addCharacters((int) orderItem.getTotal()),
                                orderItem.getDate());
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------  TỔNG TIỀN: " + ProductService.addCharacters(totalMoney) + "  --------------------");
                orderView.showBill(totalMoney);
    }
}
