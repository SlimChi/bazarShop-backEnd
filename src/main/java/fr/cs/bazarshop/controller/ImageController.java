package fr.cs.bazarshop.controller;

import fr.cs.bazarshop.entity.Image;
import fr.cs.bazarshop.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/save")
    public String saveIMageOrFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name
                                  ) throws IOException {
        return imageService.saveImage(file, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok(image.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllImages() {
        List<Image> images = imageService.getAllImages();

        if (images.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucune image n'est présente dans la base de données.");
        } else {
            return ResponseEntity.ok(images);
        }
    }

}
