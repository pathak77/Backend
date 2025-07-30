package com.example.Ecommerce.Mapper;


import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Dto.ProductResourceDto;
import com.example.Ecommerce.Dto.ProductTypeDto;
import com.example.Ecommerce.Entity.*;
import com.example.Ecommerce.Service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class ProductMapper {

    @Autowired
    CategoryServiceImpl categoryService;

    public Product mapToProductEntity(ProductDto productDto){
        Product product = new Product();
        if( productDto.getProduct_id() != null ){
            product.setProduct_id(productDto.getProduct_id());
        }
        product.setProduct_name(productDto.getProduct_name());
        product.setProduct_description(productDto.getProduct_description());
        product.setNewArrival(productDto.isNewArrival());
        product.setProduct_price(productDto.getProduct_price());
        product.setRating(productDto.getRating());
        product.setSlug(productDto.getSlug());
        product.setProduct_image(productDto.getProduct_image());
        Category category = categoryService.getCategory(productDto.getCategoryId());

        if( category != null ){
            product.setCategory(category);
            UUID categoryTypeId = productDto.getCategoryTypeId();

            CategoryType categoryType = category.getCategoryTypes().stream().filter(categoryType1 -> categoryType1.getId().equals(categoryTypeId)).findFirst().orElse(null);
            product.setCategoryType(categoryType);
        }

        if( productDto.getTypes() != null ){
            product.setProductType(mapToProductType(productDto.getTypes(),product));
        }

        if(productDto.getProductResources() != null ){
            product.setResources(mapToProductResources(productDto.getProductResources(),product));
        }

        return product;
    }

    public ProductDto mapToDto(Product product){
        return ProductDto.builder()
                .product_id(product.getProduct_id())
                .product_name(product.getProduct_name())
                .product_price(product.getProduct_price())
                .isNewArrival(product.isNewArrival())
                .rating(product.getRating())
                .product_description(product.getProduct_description())
                .slug(product.getSlug())
                .product_image(product.getProduct_image())
                .build();
    }

    public Product mapToCategory(ProductDto productDto){
        return Product.builder()
                .product_id(productDto.getProduct_id())
                .product_name(productDto.getProduct_name())
                .product_description(productDto.getProduct_description())
                .build();
    }

    public ProductResourceDto mapResourceToDto(Resources resources) {
        return ProductResourceDto.builder()
                .id(resources.getId())
                .url(resources.getUrl())
                .name(resources.getName())
                .isPrimary(resources.getIsPrimary())
                .type(resources.getType())
                .build();
    }

    private List<ProductType> mapToProductType(List<ProductTypeDto> ProductTypeDtos, Product product){
        return ProductTypeDtos.stream().map(ProductTypeDto -> {
            ProductType ProductType = new ProductType();
            if( ProductTypeDto.getId() != null){
                ProductType.setId(ProductTypeDto.getId());
            }
            ProductType.setColor(ProductTypeDto.getColor());
            ProductType.setSize(ProductTypeDto.getSize());
            ProductType.setStockQuantity(ProductTypeDto.getStockQuantity());
            ProductType.setProduct(product);
            return ProductType;
        }).collect(Collectors.toList());
    }

    public List<Resources> mapToProductResources(List<ProductResourceDto> productResources, Product product) {

        return productResources.stream().map(productResourceDto -> {
            Resources resources= new Resources();
            if( productResourceDto.getId() != null){
                resources.setId(productResourceDto.getId());
            }
            resources.setName(productResourceDto.getName());
            resources.setType(productResourceDto.getType());
            resources.setUrl(productResourceDto.getUrl());
            resources.setIsPrimary(productResourceDto.getIsPrimary());
            resources.setProduct(product);
            return resources;
        }).collect(Collectors.toList());
    }

    public List<ProductTypeDto> mapProductTypeListToDto(List<ProductType> ProductType) {
        return ProductType.stream().map(this::mapProductTypeDto).toList();
    }

    public List<ProductResourceDto> mapResourceListToDto(List<Resources> productResources) {
        return productResources.stream().map(this::mapResourceToDto).toList();
    }

    private ProductTypeDto mapProductTypeDto(ProductType ProductType) {
        return ProductTypeDto.builder()
                .color(ProductType.getColor())
                .id(ProductType.getId())
                .size(ProductType.getSize())
                .stockQuantity(ProductType.getStockQuantity())
                .build();
    }
}
