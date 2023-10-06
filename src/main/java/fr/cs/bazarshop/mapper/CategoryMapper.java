package fr.cs.bazarshop.mapper;

import fr.cs.bazarshop.dto.CategoryDto;
import fr.cs.bazarshop.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);
}

