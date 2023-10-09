package fr.cs.bazarshop.service;

import fr.cs.bazarshop.dto.ProductDto;
import fr.cs.bazarshop.entity.Product;
import fr.cs.bazarshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> findAllProducts();
    Optional<ProductDto> findProductById(Integer id); // Nouvelle méthode pour rechercher un produit par ID
    void deleteProduct(Integer id); // Nouvelle méthode pour supprimer un produit par ID
    ProductDto updateProduct(Integer id, ProductDto productDto);


    ProductDto updateProductWithImage(Integer id, ProductDto updatedProductDto);
}
