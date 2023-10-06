package fr.cs.bazarshop.mapper.mapperImpl;


import fr.cs.bazarshop.dto.ImageDto;
import fr.cs.bazarshop.entity.Image;
import fr.cs.bazarshop.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageDto toDto(Image image) {
        return new ImageDto(
                image.getImageId(),
                image.getName()
        );
    }

    @Override
    public List<ImageDto> toDtoList(List<Image> images) {
        List<ImageDto> dtoList = new ArrayList<>();
        images.stream().forEach(image -> dtoList.add(toDto(image)));
        return dtoList;
    }

    @Override
    public Image toImageOnlyId(ImageDto imageDto) {
        return new Image(imageDto.getImageId());
    }

}
