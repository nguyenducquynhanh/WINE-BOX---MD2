package caseMD2.services;

import caseMD2.model.Order;
import caseMD2.model.OrderItem;
import caseMD2.utils.CSVUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderService implements IOrderService{
    public static ArrayList<Order> listOrder = new ArrayList<>();
    private static final OrderItemService orderItemServices = new OrderItemService();
    public static final String fileName = "F:\\MD2\\CaseMD2\\data\\order";

    @Override
    public List<Order> getOrders() {
        if (listOrder.size() == 0) {
            List<String> records = CSVUtils.read(fileName);

            for (String record : records) {
                listOrder.add(new Order(record));
            }
            return listOrder;
        }
        return null;
    }

    @Override
    public void add(Order newOrder) {
        listOrder.add(newOrder);
        update();
    }

    @Override
    public void update() {
        CSVUtils.write(fileName, listOrder);
    }

    @Override
    public Order getOrderById(int id) {
        for (Order order : listOrder) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean exist(int id) {
        return getOrderById(id) != null;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (Order order : listOrder) {
            if (order.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean checkDuplicateId(int id) {
        for (Order order : listOrder) {
            if (order.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(Order order) {
        listOrder.remove(order);
        CSVUtils.write(fileName, listOrder);
    }

    private static boolean isEmpty(ArrayList<Order> listOrder) {
        return listOrder.size() == 0;
    }
    public static boolean checkExistNameOfOrder(String name) {
        for (Order order : listOrder) {
            if (order.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }



    public static void showListOrder() {
        if (isEmpty(listOrder)) {
            System.out.println("----------Danh sách sản phẩm đặt hàng trống!----------");
            System.out.println(" ");
        } else {
            System.out.println("--------------------------------------------------------------  LỊCH SỬ ĐẶT HÀNG  --------------------------------------------------------------");
            for (Order order : listOrder) {
                int totalMoney = 0;
                System.out.printf("*   %-25s %-1s %-15s %-1s %-15s %-1s %-15s %-1s %-30s  * \n", "Thông tin người dùng", "ID:"
                        , order.getId(), "Tên:", order.getName(),
                        "Số điện thoại:", order.getPhone(), "Địa chỉ:", order.getAddress());
                System.out.printf("*%-142s*\n", "");
                System.out.printf("*            %-12s %-22s %-12s %-17s %-20s %-41s *\n", "ID",
                        "Tên sản phẩm", "Giá", "Số lượng", "Tổng tiền", "Thời gian đặt");
                for (OrderItem orderItem : OrderItemService.listOrderPaid) {
                    if (order.getName().equals(orderItem.getName())){
                        totalMoney += orderItem.getTotal();
                        System.out.printf("*       %-16s %-22s %-15s  %-13s %-20s %-42s *\n", orderItem.getId(), orderItem.getProductName(),
                                ProductService.addCharacters((int) orderItem.getPrice()), orderItem.getQuantity(),
                                ProductService.addCharacters((int) orderItem.getTotal()), orderItem.getDate());
                    }
                }
                System.out.println("---------------------------------------------------------------------------TỔNG TIỀN: "
                        + ProductService.addCharacters(totalMoney) + " ------------------------------------------------");
                System.out.println(" ");
            }
        }
    }

    public static String formatDatetime(long ms) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH) + 1;
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int mMinus = calendar.get(Calendar.MINUTE);
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);

        String dateTime = Integer.toString(mHour);
        if (Integer.toString(mMinus).length() == 1) {
            dateTime += ":0" + mMinus;
        } else {
            dateTime += ":" + mMinus;
        }

        if (Integer.toString(mDay).length() == 1) {
            dateTime += " 0" + mDay;
        } else {
            dateTime += " " + mDay;
        }

        if (Integer.toString(mMonth).length() == 1) {
            dateTime += "/0" + mMonth;
        } else {
            dateTime += "/" + mMonth;
        }

        return dateTime + "/" + mYear;
    }

//    public void removeOrderByName(String name) {
//        if (!OrderItemService.isExistNameOfOrderItemList(name)) {
//            listOrder.removeIf(order -> order.getName().equals(name));
//            CSVUtils.write(fileName, listOrder);
//        }
//    }
}
