package com.example.Ecommerce.Service;


import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Exceptions.ProductNotFoundException;
import com.example.Ecommerce.Mapper.ProductMapper;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Repo.CategoryRepo;
import com.example.Ecommerce.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService  {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = productMapper.mapToProductEntity(productDto);

        return  productRepo.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts(UUID categoryId, UUID typeId) throws Exception {
        List<ProductDto> list = new ArrayList<>();
        productRepo.findAll().forEach(product -> {
            boolean matchCategoryId = product.getCategory().getId().equals(categoryId);
            boolean matchTypeId = product.getCategoryType().getId().equals(typeId);

            if( matchCategoryId && matchTypeId ) {
                ProductDto productDto = productMapper.mapToDto(product);
                list.add( productDto );
            }
        });
        return list;
    }

    @Override
    public ProductDto getProductBySlug(String slug) throws Exception {
        Product product= productRepo.findBySlug(slug);
        if(product == null){
            throw new ProductNotFoundException();
        }
        ProductDto productDto = productMapper.mapToDto(product);
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryTypeId(product.getCategoryType().getId());
        productDto.setTypes(productMapper.mapProductTypeListToDto(product.getProductType()));
        productDto.setProductResources(productMapper.mapResourceListToDto(product.getResources()));
        return productDto;
    }

    @Override
    public ProductDto getProductById(UUID id) throws ProductNotFoundException {
        Product product= productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
        ProductDto productDto = productMapper.mapToDto(product);
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryTypeId(product.getCategoryType().getId());
        productDto.setTypes(productMapper.mapProductTypeListToDto(product.getProductType()));
        productDto.setProductResources(productMapper.mapResourceListToDto(product.getResources()));
        return productDto;
    }

    @Override
    public Product updateProduct(ProductDto productDto, UUID id) throws ProductNotFoundException {
        Product product= productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
        productDto.setProduct_id(product.getProduct_id());
        return productRepo.save(productMapper.mapToProductEntity(productDto));
    }

    @Override
    public Product fetchProductById(UUID uuid) throws Exception {
        return productRepo.findById(uuid).orElseThrow(ProductNotFoundException::new);
    }
}
