package com.example.Ecommerce.Service;


import com.example.Ecommerce.Dto.CategoryDto;
import com.example.Ecommerce.Dto.CategoryTypeDto;
import com.example.Ecommerce.Model.Category;
import com.example.Ecommerce.Model.CategoryType;

import java.util.List;
import java.util.UUID;

public interface CatetgoryService {

        public Category getCategory(long categoryId);

        public Category createCategory(CategoryDto categoryDto);

        public Category mapToEntity(CategoryDto categoryDto);

        public List<CategoryType> mapToCategoryTypesList(List<CategoryTypeDto> categoryTypeList, Category category);

        public List<Category> getAllCategory();

        public Category updateCategory(CategoryDto categoryDto, UUID categoryId);

        public void deleteCategory(UUID categoryId);
 }
