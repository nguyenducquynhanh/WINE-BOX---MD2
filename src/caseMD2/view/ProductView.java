package caseMD2.view;

import caseMD2.menu.Menu;
import caseMD2.model.Product;
import caseMD2.services.ProductService;
import caseMD2.utils.CSVUtils;
import caseMD2.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.Scanner;

import static caseMD2.menu.Menu.scanner;
import static caseMD2.services.ProductService.products;
import static caseMD2.services.ProductService.showAllProducts;

public class ProductView {
//    public static void runProduct(){
//        do {
//            Scanner scanner = new Scanner(System.in);
//            ProductView productView = new ProductView();
//            Menu.menuUser();
//            try {
//                System.out.println("\nChọn chức năng");
//                System.out.println(" ⭆ ");
//                int number = Integer.parseInt(scanner.nextLine());
//                switch (number) {
//                    case 1:
//                        productView.addProduct();
//                        break;
//                    case 2:
//                        productView.editProduct();
//                        break;
//                    case 3:
//                        productView.deleteProduct();
//                        break;
//                    case 4:
//                        productView.showProduct();
//                        break;
//                    case 8:
//                        Menu.mainMenu();
//                        break;
//                    case 0:
//                        System.exit(0);
//                        break;
//                    default:
//                        System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
//            }
//        }while(true);
//    }

    private static final ProductService productService = new ProductService();

    public static void runProduct() {
        do {
            Scanner scanner = new Scanner(System.in);
            ProductView managerProductView = new ProductView();
            Menu.menuProduct();
            try {
                System.out.println("\nChọn chức năng");
                System.out.print(" ⭆ ");

                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        managerProductView.addProduct();
                        break;
                    case 2:
                        managerProductView.updateProduct();
                        break;
                    case 3:
                        managerProductView.deleteProduct();
                        break;
                    case 4:
                        managerProductView.searchProduct();
                        break;
                    case 5:
                        managerProductView.showProduct();
                        break;
                    case 8:
                        Menu.mainMenu();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
            }
        } while (true);
    }

    private void searchProduct() {
        ArrayList<Product> searchProduct = new ArrayList<>();
        System.out.println("Nhập thông tin cần tìm: ");
        String name = scanner.nextLine().toLowerCase();
        for (Product product : products) {
            if (product.toString().toLowerCase().contains(name)) {
                searchProduct.add(product);
            }
        }
        ProductService.showSearch(searchProduct);
    }

    public void showProduct() {
        try {
            showAllProducts();
        } catch (Exception e) {
            System.out.println("Chọn chức năng không đúng!");
        }
    }

    public void addProduct() {
        int quantity = 0;
        long price = 0;
//        do {
//            try {
//                System.out.println("Nhập ID: ");
//                id = Integer.parseInt(scanner.nextLine());
//
//                while (productService.existById(id) || id <= 0) {
//                    if (id <= 0) {
//                        System.out.println("ID không hợp lệ! Vui lòng nhập lại ID: ");
//                    } else {
//                        System.out.println("ID đã tồn tại! Nhập ID khác: ");
//                    }
//                    id = Integer.parseInt(scanner.nextLine());
//                }
//            } catch (Exception e) {
//                System.out.println("ID không hợp lệ! Vui lòng nhập lại ID!");
//                id = 0;
//            }
//        } while (!productService.existById(id));
        String name = null;
        do {
            try {
                System.out.println("Nhập tên (viết hoa chữ cái đầu của mỗi từ, VD: Vermouth): ");
                name = scanner.nextLine();

                while (productService.existByName(name.toLowerCase()) || !ValidateUtils.isNameValid(name)) {
                    if (!ValidateUtils.isNameValid(name)) {
                        System.out.println("Tên không hợp lệ! Vui lòng nhập lại: ");
                    } else {
                        System.out.println("Tên đã tồn tại! Nhập tên khác: ");
                    }
                    name = scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Tên không hợp lệ! Vui lòng nhập lại tên!");
            }
        } while (productService.existByName(name));


        do {
            try {
                System.out.println("Nhập giá: ");
                price = Long.parseLong(scanner.nextLine());
                while (price <= 0) {
                    System.out.println("Giá không hợp lý! Nhập lại: ");
                    price = Long.parseLong(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Giá không hợp lý! Vui lòng nhập lại giá!");
            }
        } while (price <= 0);

        do {
            try {
                System.out.println("Nhập số lượng: ");
                quantity = Integer.parseInt(scanner.nextLine());
                while (quantity < 0) {
                    System.out.println("Số lượng không hợp lý! Nhập lại: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                }
                Product product = new Product(name, price, quantity);
                productService.add(product);
                System.out.println("Sản phẩm đã được thêm thành công!");
                showAllProducts();
            } catch (Exception e) {
                System.out.println("Số lượng không hợp lý! Vui lòng nhập lại số lượng!");
            }
        } while (quantity < 0);
    }


    public void updateProduct() {
        try {
            showAllProducts();
            System.out.println("* * * * * * NHẤN 0 ĐỂ QUAY LẠI MENU GẦN NHẤT * * * * * * ");
            int id = 0;
            do {
                try {
                    System.out.println("Nhập ID muốn sửa: ");
                    id = Integer.parseInt(scanner.nextLine());
                    while (!productService.existById(id)) {
                        if (id == 0) {
                            runProduct();
                        } else {
                            System.out.println("Không tồn tại sản phẩm có ID này! Vui lòng nhập lại ID khác: ");
                        }
                        id = Integer.parseInt(scanner.nextLine());
                    }
                } catch (Exception e) {
                    System.out.println("ID không hợp lệ!");
                }
            } while (!productService.existById(id));
            Product product = productService.getById(id);
            String name = checkNameProduct(product);
            long price = checkPriceProduct(product);
            int quantity = checkQuantity(product);

            if (name.equals("0") || Long.toString(price).equals("0") || Integer.toString(quantity).equals("0")) {
                System.out.println("Sản phẩm chưa được thay đổi!");
            } else {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                System.out.println("Sản phẩm đã được chỉnh sửa thành công!");
                showAllProducts();
                productService.update();
            }
        } catch (Exception e) {
            System.out.println("Thông tin nhập không hợp lệ!");
        }
    }

    private int checkQuantity(Product product) {
        int quantity = 0;
        do {
            try {
                System.out.println("Nhập số lượng sản phẩm muốn sửa (bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
                quantity = Integer.parseInt(scanner.nextLine());

                while (quantity <= 0 || product.getQuantity() == quantity) {
                    if (quantity == 0) {
                        runProduct();
                    } else if (product.getQuantity() == quantity) {
                        System.out.println("Số lượng thay đổi phải khác ban đầu! Vui lòng nhập lại số lượng sản phẩm: ");
                    } else {
                        System.out.println("Số lượng không thể âm! Vui lòng nhập lại số lượng: ");
                    }
                    quantity = Integer.parseInt(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Số lượng không hợp lệ!");
            }
        } while (quantity <= 0 || product.getQuantity() == quantity);
        product.setQuantity(quantity);
        return quantity;
    }

    private long checkPriceProduct(Product product) {
        long price = 0;
        do {
            try {
                System.out.println("Nhập giá mới (bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
                price = Long.parseLong(scanner.nextLine());

                while (price <= 0 || product.getPrice() == price) {
                    if (price == 0) {
                        runProduct();
                    } else if (product.getPrice() == price) {
                        System.out.println("Giá thay đổi phải khác ban đầu! Vui lòng nhập lại giá sản phẩm: ");
                    } else {
                        System.out.println("Giá sản phẩm không được âm! Vui lòng nhập lại giá sản phẩm: ");
                    }

                    price = Long.parseLong(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Giá sản phẩm không hợp lệ!");
            }
        } while (price <= 0 || product.getPrice() == price);
        product.setPrice(price);
        return price;
    }

    private String checkNameProduct(Product product) {
        System.out.println("Nhập tên mới (bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
        String name = scanner.nextLine();

        while (product.getName().equals(name)||!ValidateUtils.isNameValid(name)) {
            if (name.equals("0")) {
                runProduct();
            } else if (product.getName().equals(name)) {
                System.out.println("Tên thay đổi không được trùng với tên ban đầu!");
            }else{
                System.out.println("Tên không hợp lệ!");
            }
            name = scanner.nextLine();
        }
        return name;
    }


    public void deleteProduct() {
        try {
            System.out.println("Nhập ID muốn xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            while (!productService.existById(id)) {
                System.out.println("Không tồn tại sản phẩm có ID này! Vui lòng nhập lại ID khác: ");
                id = Integer.parseInt(scanner.nextLine());
            }
            productService.removeById(id);
            System.out.println("Sản phẩm có ID là " + id + " đã được xóa khỏi danh sách!");
            showAllProducts();
        } catch (Exception e) {
            System.out.println("ID không hợp lệ!");
        }
    }

//    public void searchProductName() {
//        ArrayList<Product> searchPrName = new ArrayList<>();
//        System.out.println("Nhập tên cần tìm: ");
//        String name = scanner.nextLine().toLowerCase();
//        for (Product product : products) {
//            if (product.getName().toLowerCase().contains(name)) {
//                searchPrName.add(product);
//            }
//        }
//        ProductService.showSearch(searchPrName);
//    }
//
//    public void searchProductID() {
//        ArrayList<Product> searchPrID = new ArrayList<>();
//        System.out.println("Nhập ID cần tìm: ");
//        int id = Integer.parseInt(scanner.nextLine());
//        while (!productService.existById(id)) {
//            System.out.println("Không tồn tại sản phẩm có ID này! Vui lòng nhập lại ID khác: ");
//            id = Integer.parseInt(scanner.nextLine());
//        }
//        ProductService.showSearch(searchPrID);
//    }

//    public void searchProduct() {
//        ArrayList<Product> searchProduct = new ArrayList<>();
//        System.out.println("Nhap thong tin can tim: ");
//        String str = scanner.nextLine().toLowerCase();
//    }
}
