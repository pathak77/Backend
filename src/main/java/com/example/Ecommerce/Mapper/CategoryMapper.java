package com.example.Ecommerce.Mapper;


import com.example.Ecommerce.Dto.CategoryDto;
import com.example.Ecommerce.Model.Category;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryMapper {

    public CategoryDto mapToDto(Category category){
        return CategoryDto.builder().id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
    }

    public Category mapToCategory(CategoryDto categoryDto){
        return Category.builder()
                        .id(categoryDto.getId())
                        .name(categoryDto.getName())
                        .code(categoryDto.getCode())
                        .description(categoryDto.getDescription())
                        .build();
    }
}
