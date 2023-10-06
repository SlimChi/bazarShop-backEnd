package fr.cs.bazarshop.mapper;


import fr.cs.bazarshop.dto.ImageDto;
import fr.cs.bazarshop.entity.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    ImageDto toDto(Image image);

    List<ImageDto> toDtoList(List<Image> images);

    Image toImageOnlyId(ImageDto imageDto);


}
