package org.product;



import org.product.ProductModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.listAll();
    }

    public ProductModel getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public ProductModel createProduct(ProductModel productModel) {
        productRepository.persist(productModel);
        return productModel;
    }

    @Transactional
    public ProductModel updateProduct(Long id, ProductModel updatedProductModel) {
        ProductModel productModel = productRepository.findById(id);
        if (productModel != null) {
            productModel.setName(updatedProductModel.getName());
            productModel.setPrice(updatedProductModel.getPrice());
            productModel.setQuantity(updatedProductModel.getQuantity());
        }
        return productModel;
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }
}