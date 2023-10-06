package fr.cs.bazarshop.service;

import fr.cs.bazarshop.dto.ImageDto;
import fr.cs.bazarshop.entity.Image;
import fr.cs.bazarshop.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {

@Transactional
    String saveImage(MultipartFile file, String name) throws IOException;

    ImageDto saveImageTest(MultipartFile file) throws IOException;

    Image getImageById(Long id);

    Image getImageByName(String name);

    ImageDto getImageId(Long image_id);


    List<Image> getAllImages();
}
