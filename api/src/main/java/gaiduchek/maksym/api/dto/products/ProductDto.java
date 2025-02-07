package gaiduchek.maksym.api.dto.products;

import gaiduchek.maksym.api.dto.BaseInfoDto;
import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
import gaiduchek.maksym.api.validation.groups.UsedInOtherGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.groups.ConvertGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {

    private Long id;
    @NotEmpty(message = "Product name must not be empty", groups = {CreateGroup.class})
    private String name;
    private String description;
    @NotNull(message = "Product company must not be null", groups = {CreateGroup.class})
    @ConvertGroup(from = CreateGroup.class, to = UsedInOtherGroup.class)
    @Valid
    private BaseInfoDto company;
    @NotNull(message = "Product cost must not be null", groups = {CreateGroup.class, UpdateGroup.class})
    @Positive(message = "Product cost must be positive", groups = {CreateGroup.class, UpdateGroup.class})
    private BigDecimal cost;
    @NotNull(message = "Product count must not be null", groups = {CreateGroup.class, UpdateGroup.class})
    @Positive(message = "Product count must be positive", groups = {CreateGroup.class, UpdateGroup.class})
    private Integer count;
    private Boolean deleted;
    @ConvertGroup(from = UpdateGroup.class, to = UsedInOtherGroup.class)
    @Valid
    private List<FilterDto> filters;
    private ImageDto image;
}
