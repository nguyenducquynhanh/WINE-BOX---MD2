package caseMD2.services;

import caseMD2.model.Product;

import java.util.ArrayList;

public interface IProductServices {
    ArrayList<Product> getProducts();

    void add(Product newProduct);

    Product getById(long id);

    boolean existById(long id);


    void removeById(long id);
}
