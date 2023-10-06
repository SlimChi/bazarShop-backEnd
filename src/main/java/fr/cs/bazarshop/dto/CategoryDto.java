package fr.cs.bazarshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;

    private String categoryName;

    public void setId(Integer id) {
        this.categoryId = id;
    }
}
