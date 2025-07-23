package com.example.Ecommerce.Controller;


import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Exceptions.ProductNotFoundException;
import com.example.Ecommerce.Model.Product;
import com.example.Ecommerce.Service.ProductServiceImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class ProductController {


    ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false,name = "categoryId",value = "categoryId") UUID categoryId,
            @RequestParam(required = false,name = "typeId",value = "typeId") UUID typeId,
            @RequestParam(required = false) String slug,
            HttpServletResponse response) throws Exception {

        System.out.println("get all products i.e. /products api has been hit");
        List<ProductDto> productList = new ArrayList<>();
        if(StringUtils.isNotBlank(slug)){
            ProductDto productDto = productService.getProductBySlug(slug);
            productList.add(productDto);
        }
        else {
            try {
                productList = productService.getAllProducts(categoryId, typeId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        response.setHeader("Content-Range",String.valueOf(productList.size()));
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) throws ProductNotFoundException {
        ProductDto productDto = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        Product product = productService.addProduct(productDto);

        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto,@PathVariable UUID id) throws ProductNotFoundException{
        Product product = productService.updateProduct(productDto,id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
