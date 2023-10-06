package fr.cs.bazarshop.service.serviceImpl;

import fr.cs.bazarshop.dto.ProductDto;
import fr.cs.bazarshop.entity.Product;
import fr.cs.bazarshop.exeption.ProductNotFoundException;
import fr.cs.bazarshop.mapper.ProductMapper;
import fr.cs.bazarshop.repository.ProductRepository;
import fr.cs.bazarshop.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {

        // Convertissez ProductDto en entité Product
        Product product = productMapper.toProduct(productDto);

        // Enregistrez le produit dans la base de données
        product = productRepository.save(product);



        // Enregistrez le produit dans la base de données
        product = productRepository.save(product);

        // Convertissez l'entité Product en ProductDto et retournez-le
        return productMapper.toProductDto(product);
    }


    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<ProductDto> findProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::toProductDto);
    }

//    @Override
//    public void deleteProduct(Integer id) {
//        productRepository.deleteById(id);
//    }

    @Override
    public void deleteProduct(Integer invoiceId) {
        Optional<Product> optionalInvoice = Optional.ofNullable(productRepository.findById(invoiceId).orElseThrow(() -> new ProductNotFoundException("Le produit avec l'ID spécifié n'existe pas")));
        if (optionalInvoice.isPresent()) {
            productRepository.delete(optionalInvoice.get());
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        Optional<Product> existingProduct = Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Le produit avec l'ID spécifié n'existe pas")));

        if (existingProduct.isPresent()) {
            Product updatedProduct = productMapper.toProduct(productDto);
            updatedProduct.setId(id); // Assurez-vous que l'ID reste le même
            Product savedProduct = productRepository.save(updatedProduct);
            return productMapper.toProductDto(savedProduct);
        } else {
            throw new IllegalArgumentException();

        }
    }


}
