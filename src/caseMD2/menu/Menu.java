package caseMD2.menu;

import caseMD2.model.User;
import caseMD2.services.OrderItemService;
import caseMD2.services.OrderService;
import caseMD2.services.ProductService;
import caseMD2.services.UserService;
import caseMD2.view.UserView;
import caseMD2.view.ProductView;
import caseMD2.view.OrderView;

import java.util.Scanner;

public class Menu {
    private static final UserService userService = new UserService();
    private static final ProductService productService = new ProductService();

    private static final OrderItemService orderItemService = new OrderItemService();
    private static final OrderService orderService = new OrderService();
    public static Scanner scanner = new Scanner(System.in);
    public static long userId;
    public static void show() {
        System.out.println();
        System.out.println("    ✿ ✿ ✿ ✿ ⇒⇒⇒ MENU BOX ⇐⇐⇐ ✿ ✿ ✿ ✿");
        System.out.println("    ✿                                   ✿");
        System.out.println("    ✿      1. Quản lí người dùng        ✿");
        System.out.println("    ✿      2. Quản lí loại rượu         ✿");
        System.out.println("    ✿      3. Quản lí hóa đơn           ✿");
        System.out.println("    ✿      0. Đăng xuất                 ✿");
        System.out.println("    ✿                                   ✿");
        System.out.println("    ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
    }
    public static void mainMenu() {
        int option;
        do {
            show();
            try {
                System.out.println("\nChọn chức năng ");
                System.out.print(" ⭆ ");
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        UserView.runUser();
                        break;
                    case 2:
                        ProductView.runProduct();
                        break;
                    case 3:
                        OrderView.runOrder();
                        break;
                    case 0:
                        login();
                        break;
                    default:
                        System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
                        break;
                }
            }catch (Exception e){
                System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
            }
        } while (true);
    }

    public static void orderMenu() {
        System.out.println("    ✿ ✿ ✿ ✿ ⇒⇒⇒ ORDER MENU ⇐⇐⇐ ✿ ✿ ✿ ✿");
        System.out.println("    ✿                                     ✿");
        System.out.println("    ✿      1. Tạo order                   ✿");
        System.out.println("    ✿      2. Xem danh sách order         ✿");
        System.out.println("    ✿      0. Quay lại                    ✿");
        System.out.println("    ✿                                     ✿");
        System.out.println("    ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
    }

    public static void menuUser() {
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ⇒⇒ USERS MANAGER ⇐⇐ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.println("✿                                                      ✿");
        System.out.println("✿     1. Thêm người dùng                               ✿");
        System.out.println("✿     2. Sửa thông tin người dùng                      ✿");
        System.out.println("✿     3. Xóa người dùng                                ✿");
        System.out.println("✿     4. Hiện danh sách người dùng                     ✿");
        System.out.println("✿                                                      ✿");
        System.out.println("✿ Nhấn '8' để trở lại \t|\t'0' để thoát chương trình  ✿");
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
    }

    public static void menuProduct() {
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ⇒⇒ PRODUCTS MANAGER ⇐⇐ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.println("✿                                                            ✿");
        System.out.println("✿     1. Thêm loại                                           ✿");
        System.out.println("✿     2. Sửa thông tin loại                                  ✿");
        System.out.println("✿     3. Xóa loại                                            ✿");
        System.out.println("✿     4. Tìm kiếm sản phẩm                                   ✿");
        System.out.println("✿     5. Hiện danh sách tất cả các loại                      ✿");
        System.out.println("✿                                                            ✿");
        System.out.println("✿    Nhấn '8' để trở lại \t|\t'0' để thoát chương trình    ✿");
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
    }

    public static void menuUpdateUser() {
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ⇒⇒ PRODUCTS MANAGER ⇐⇐ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.println("✿                                                           ✿");
        System.out.println("✿     1. Sửa tất cả thông tin                               ✿");
        System.out.println("✿     2. Sửa một thông tin                                  ✿");
        System.out.println("✿                                                           ✿");
        System.out.println("✿    Nhấn '8' để trở lại \t|\t'0' để thoát chương trình   ✿");
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.print(" ⭆ ");
    }

    public static void menuUpdateInfoUser(){
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ⇒⇒ PRODUCTS MANAGER ⇐⇐ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.println("✿                                                           ✿");
        System.out.println("✿              1. Mật khẩu                                  ✿");
        System.out.println("✿              2. Tên                                       ✿");
        System.out.println("✿              3. Số điện thoại                             ✿");
        System.out.println("✿              4. Email                                     ✿");
        System.out.println("✿              5. Địa chỉ                                   ✿");
        System.out.println("✿                                                           ✿");
        System.out.println("✿    Nhấn '8' để trở lại \t|\t'0' để thoát chương trình   ✿");
        System.out.println("✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿ ✿");
        System.out.print(" ⭆ ");
    }

    public static void login() {
        orderService.getOrders();
        orderItemService.getOrderItems();
        OrderItemService.getOrdersPaid();
        userService.getUsers();
        productService.getProducts();
        UserService userServices = new UserService();
        System.out.println("******************** ĐĂNG NHẬP HỆ THỐNG ********************");
        System.out.print("⭆ Username: ");
        String userName = scanner.nextLine();
        System.out.print("⭆ Password: ");
        String password = scanner.nextLine();
        if (userServices.loginAdmin(userName, password) != null) {
            User user = userServices.getUserByUserName(userName);
            userId = user.getId();
            System.out.println("Bạn đã đăng nhập thành công! \uD83C\uDF8A \n");
            System.out.println("    \uD83C\uDF77 CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI WINE BOX \uD83C\uDF77 \n");
            mainMenu();
        }else {
            System.out.println("Tài khoản hoặc mật khẩu không đúng! Vui lòng đăng nhập lại! ");
            login();
        }
    }

}

