package caseMD2.services;

import caseMD2.model.Product;
import caseMD2.utils.CSVUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductServices {
    public static ArrayList<Product> products = new ArrayList<>();
    private final String path = "F:\\MD2\\CaseMD2\\data\\product.csv";

    @Override
    public ArrayList<Product> getProducts() {
        if (products.size() == 0) {
            List<String> records = CSVUtils.read(path);

            for (String record : records) {
                products.add(new Product(record));
            }
            return products;
        }
        return null;
    }

    @Override
    public void add(Product newProduct) {
        products.add(newProduct);
        update();
    }

    @Override
    public Product getById(long id) {
        for (Product product : products) {
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }

    public void update(){
        CSVUtils.write(path,products);
    }

    @Override
    public boolean existById(long id) {
        for (Product product : products) {
            if (id == product.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeById(long id) {
        int index = findIndexById(id);
        products.remove(index);
        update();
    }

    public int findIndexById(long id) {
        for (int i = 0; i < products.size(); i++) {
            if (id == products.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }
    public boolean existByName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String addCharacters(long price) {
        String patternTienTe = ",###₫";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        return formatTienTe.format(price);
    }
    public static void showAllProducts() {
        System.out.println("------------------------- DANH SÁCH SẢN PHẨM --------------------------");
        System.out.printf("*%-69s*\n", "");
        System.out.printf("*        %-16s %-15s %-15s %-12s*\n", "ID", "Tên sản phẩm", "Giá", "Số lượng");
        System.out.printf("*%-69s*\n", "");

        for (Product product : products) {
            System.out.printf("*        %-16s %-15s %-17s %-10s*\n", product.getId(), product.getName(), addCharacters(product.getPrice()), product.getQuantity());
        }
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------------------");
    }

    public static void showSearch(ArrayList<Product> list) {
        if (list.size() == 0) {
            System.out.println(" ");
            System.out.println("==================  Danh sách tìm kiếm trống!  ==================");
            System.out.println(" ");
        } else {
            System.out.println("------------------------- DANH SÁCH TÌM KIẾM -------------------------");
            System.out.printf("*%-69s*\n", "");
            System.out.printf("*        %-16s %-15s %-15s %-12s*\n", "ID", "Tên sản phẩm", "Giá", "Số lượng");
            System.out.printf("*%-69s*\n", "");

            for (Product product : list) {
                System.out.printf("*        %-16s %-15s %-17s %-10s*\n", product.getId(), product.getName(), addCharacters(product.getPrice()), product.getQuantity());
            }
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------");
        }
    }

}
