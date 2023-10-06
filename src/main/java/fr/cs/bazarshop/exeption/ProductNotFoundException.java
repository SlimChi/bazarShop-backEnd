package fr.cs.bazarshop.exeption;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(message);
    }

    // Vous pouvez ajouter d'autres constructeurs si nécessaire
}
