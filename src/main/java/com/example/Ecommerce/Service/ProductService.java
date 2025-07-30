package com.example.Ecommerce.Service;



import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {

    public Product addProduct(ProductDto product) throws Exception;

    public List<ProductDto> getAllProducts(UUID categoryId, UUID typeId) throws Exception;

    ProductDto getProductBySlug(String slug) throws Exception;

    ProductDto getProductById(UUID id) throws Exception;

    Product updateProduct(ProductDto productDto, UUID id) throws Exception;

    Product fetchProductById(UUID uuid) throws Exception;

}
