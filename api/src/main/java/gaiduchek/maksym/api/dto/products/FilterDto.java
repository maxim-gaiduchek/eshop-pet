package gaiduchek.maksym.api.dto.products;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FilterDto {

    private Long id;
    @NotEmpty(message = "Filter's name must not be empty")
    private String name;
    @NotNull(message = "Filter's category id must not be null")
    private Long filterCategoryId;
    private Long productsCount;
    private Boolean deleted;
}
