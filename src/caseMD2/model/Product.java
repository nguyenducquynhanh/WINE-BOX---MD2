package caseMD2.model;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private long id;
    private String name;
    private long price;
    private int quantity;

    public Product( String name, long price, int quantity) {
        this.id = System.currentTimeMillis() / 1000;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String str){
        String[] strArr = str.split(",");
        this.id = Long.parseLong(strArr[0].trim());
        this.name = strArr[1].trim();
        this.price = Long.parseLong(strArr[2].trim());
        this.quantity = Integer.parseInt(strArr[3].trim());
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity;
    }

    public static void main(String[] args) {
        Product product = new Product("Kir",20000,5);
        System.out.println(product);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && quantity == product.quantity && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }

//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }

    @Override
    public int compareTo(Product o) {
        return 0;
    }
}
