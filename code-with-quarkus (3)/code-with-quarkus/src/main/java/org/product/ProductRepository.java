package org.product;


import org.product.ProductModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductModel> {
    // Panache provides built-in methods like listAll(), findById(), persist(), deleteById()
}