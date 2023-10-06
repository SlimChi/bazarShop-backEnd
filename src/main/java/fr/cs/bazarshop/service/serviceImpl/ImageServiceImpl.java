package fr.cs.bazarshop.service.serviceImpl;

import fr.cs.bazarshop.dto.ImageDto;
import fr.cs.bazarshop.entity.Image;
import fr.cs.bazarshop.exeption.ImageNotFoundException;
import fr.cs.bazarshop.mapper.ImageMapper;
import fr.cs.bazarshop.repository.ImageRepository;
import fr.cs.bazarshop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public String saveImage(MultipartFile file, String name) throws IOException {
        Image image = new Image();
        image.setName(name);
        image.setData(file.getBytes());
        imageRepository.save(image);
        return "Image saved in DB";
    }


    @Override
    public ImageDto saveImageTest(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image = imageRepository.save(image);
        return imageMapper.toDto(image);
    }
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image getImageByName(String name) {
        return imageRepository.findByName(name);
    }

    @Override
    public ImageDto getImageId(Long image_id) {
        return imageMapper.toDto(imageRepository.findById(image_id).orElseThrow(()-> new ImageNotFoundException(image_id)));    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

}
