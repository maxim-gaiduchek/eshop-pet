package gaiduchek.maksym.api.dto.products;

import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
import gaiduchek.maksym.api.validation.groups.UsedInOtherGroup;
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

    @NotNull(message = "Filter's id must not be null", groups = UsedInOtherGroup.class)
    private Long id;
    @NotEmpty(message = "Filter's name must not be empty", groups = {CreateGroup.class, UpdateGroup.class})
    private String name;
    @NotNull(message = "Filter's category id must not be null", groups = {CreateGroup.class, UpdateGroup.class})
    private Long filterCategoryId;
    private Long productsCount;
    private Boolean deleted;
}
