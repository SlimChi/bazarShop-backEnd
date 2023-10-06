package fr.cs.bazarshop.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private Long imageId;

    private byte[] data;

    private String name;

    public ImageDto(Long imageId, String name) {
    }
}
