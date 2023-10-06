package fr.cs.bazarshop.exeption;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(Long id){super("Could not find Fichier with ID :"+id);}

}
