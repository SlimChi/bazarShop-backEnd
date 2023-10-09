package fr.cs.bazarshop.controller;


import fr.cs.bazarshop.dto.CategoryDto;
import fr.cs.bazarshop.dto.ProductDto;
import fr.cs.bazarshop.exeption.CustomError;
import fr.cs.bazarshop.exeption.ProductNotFoundException;
import fr.cs.bazarshop.exeption.ResponseWrapper;
import fr.cs.bazarshop.service.ImageStorageService;
import fr.cs.bazarshop.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/products")
@Tag(name = "products")
public class ProductController {

    private final ProductService productService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public ProductController(ProductService productService, ImageStorageService imageStorageService) {
        this.productService = productService;
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("rating") Float rating,
            @RequestParam("productQuantity") int productQuantity,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Integer categoryId)
            {

        // Créez un objet ProductDto avec les détails du produit
        ProductDto productDto = ProductDto.builder()
                .productName(productName)
                .description(description)
                .rating(rating)
                .productQuantity(productQuantity)
                .price(price)
                .categoryDto(CategoryDto.builder().categoryId(categoryId).build())
                .build();

        // Enregistrez le produit en utilisant le service ProductService
        ProductDto savedProduct = productService.createProduct(productDto);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/createProductImageUrl")
    public ResponseEntity<ProductDto> createProductImageUrl(
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("rating") Float rating,
            @RequestParam("productQuantity") int productQuantity,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Vérifiez si le fichier image est présent
        if (imageFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Traitez le fichier image ici (sauvegarde, validation, etc.)
        // Vous pouvez utiliser l'objet 'imageFile' pour accéder au fichier

        // Utilisez votre service d'enregistrement d'image pour sauvegarder l'image et obtenir son URL
        String imageUrl = imageStorageService.saveImage(imageFile);

        // Créez un objet ProductDto avec les détails du produit, y compris l'URL de l'image
        ProductDto productDto = ProductDto.builder()
                .productName(productName)
                .description(description)
                .rating(rating)
                .productQuantity(productQuantity)
                .price(price)
                .categoryDto(CategoryDto.builder().categoryId(categoryId).build())
                .imageName(imageUrl) // Ajoutez l'URL de l'image
                .build();

        // Enregistrez le produit en utilisant le service ProductService
        ProductDto savedProduct = productService.createProduct(productDto);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        try {
            byte[] imageBytes = imageStorageService.getImageBytes(imageName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Changez-le en fonction du type d'image
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Gérez les erreurs de récupération d'image ici
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/save")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/findAllProduct")
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        List<ProductDto> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseWrapper<?>> findProductById(@PathVariable Integer id) {
        Optional<ProductDto> product = productService.findProductById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(new ResponseWrapper<>(product.get()));
        } else {
            String errorMessage = "Le produit avec l'ID " + id + " n'existe pas.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(new CustomError(errorMessage)));
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(new ResponseWrapper<>(null), HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException ex) {
            String errorMessage = "Le produit avec l'ID " + id + " n'existe pas.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(new CustomError(errorMessage)));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper<ProductDto>> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productService.updateProduct(id, productDto);
            return new ResponseEntity<>(new ResponseWrapper<>(updatedProduct), HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            String errorMessage = "Le produit avec l'ID " + id + " n'existe pas.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(new CustomError(errorMessage)));
        }
    }

    @PutMapping("/updateProductImageUrl/{id}")
    public ResponseEntity<ProductDto> updateProductImageUrl(
            @PathVariable Integer id,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("rating") Float rating,
            @RequestParam("productQuantity") int productQuantity,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Vérifiez si le fichier image est présent
        if (imageFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Traitez le fichier image ici (sauvegarde, validation, etc.)
        // Vous pouvez utiliser l'objet 'imageFile' pour accéder au fichier

        // Utilisez votre service d'enregistrement d'image pour sauvegarder la nouvelle image et obtenir son URL
        String newImageUrl = imageStorageService.saveImage(imageFile);

        // Récupérez le produit existant par son ID
        Optional<ProductDto> existingProductOptional = productService.findProductById(id);

        if (existingProductOptional.isPresent()) {
            ProductDto existingProduct = existingProductOptional.get();

            // Assurez-vous d'abord d'obtenir l'objet CategoryDto correspondant à partir de categoryId
            CategoryDto categoryDto = CategoryDto.builder().categoryId(categoryId).build();

            // Mettez à jour les propriétés du produit avec les nouvelles valeurs
            existingProduct.setProductName(productName);
            existingProduct.setDescription(description);
            existingProduct.setRating(rating);
            existingProduct.setProductQuantity(productQuantity);
            existingProduct.setPrice(price);
            existingProduct.setCategoryDto(categoryDto); // Assignez l'objet CategoryDto

            // Appelez la méthode de mise à jour du service ProductService en utilisant PUT
            ProductDto updatedProduct = productService.updateProduct(id, existingProduct);

            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            // Le produit avec l'ID spécifié n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PutMapping ("/updateProductWithImage/{id}")
    public ResponseEntity<ProductDto> updateProductWithImage(
            @PathVariable Integer id,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("rating") Float rating,
            @RequestParam("productQuantity") Integer productQuantity,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Vérifiez si le fichier image est présent
        if (imageFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Traitez le fichier image ici (sauvegarde, validation, etc.)
        // Vous pouvez utiliser l'objet 'imageFile' pour accéder au fichier

        // Utilisez votre service d'enregistrement d'image pour sauvegarder la nouvelle image et obtenir son URL
        String imageUrl = imageStorageService.saveImage(imageFile);

        // Créez un objet ProductDto avec les détails mis à jour du produit, y compris la nouvelle URL de l'image
        ProductDto updatedProductDto = ProductDto.builder()
                .id(id)
                .productName(productName)
                .description(description)
                .rating(rating)
                .productQuantity(productQuantity)
                .price(price)
                .categoryDto(CategoryDto.builder().categoryId(categoryId).build())
                .imageName(imageUrl) // Ajoutez la nouvelle URL de l'image
                .build();

        // Mettez à jour le produit en utilisant le service ProductService
        try {
            ProductDto updatedProduct = productService.updateProductWithImage(id, updatedProductDto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            String errorMessage = "Le produit avec l'ID " + id + " n'existe pas.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
}