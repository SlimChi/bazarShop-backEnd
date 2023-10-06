package fr.cs.bazarshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String productName;

    private String description;

    private Float rating;

    private Integer productQuantity;

    private Double price;
    private String imageName; // Champ pour stocker le nom du fichier de l'image


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}

