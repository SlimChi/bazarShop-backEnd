package fr.cs.bazarshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String productName;
    private String description;
    private Float rating;
    private int productQuantity;
    private Double price;
    private CategoryDto categoryDto;
    private String imageName; // Champ pour le nom du fichier de l'image

    // Constructeur
}
