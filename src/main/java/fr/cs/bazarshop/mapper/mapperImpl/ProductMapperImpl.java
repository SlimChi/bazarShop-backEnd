package fr.cs.bazarshop.mapper.mapperImpl;

import fr.cs.bazarshop.dto.ProductDto;
import fr.cs.bazarshop.entity.Product;
import fr.cs.bazarshop.mapper.CategoryMapper;
import fr.cs.bazarshop.mapper.ImageMapper;
import fr.cs.bazarshop.mapper.ProductMapper;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final CategoryMapper categoryMapper;
    private final ImageMapper imageMapper;


    public ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productQuantity(product.getProductQuantity())
                .rating(product.getRating())
                .imageName(product.getImageName())
                .categoryDto(categoryMapper.toCategoryDto(product.getCategory()))
                 .build();
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .id(productDto.getId())
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .productQuantity(productDto.getProductQuantity())
                .rating(productDto.getRating())
                .imageName(productDto.getImageName())
                .category(categoryMapper.toCategory(productDto.getCategoryDto()))
                .build();
    }



}
