package gaiduchek.maksym.api.dto.products;

import gaiduchek.maksym.api.dto.users.UserBaseInfoDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FilterCategoryDto {

    private Long id;
    @NotEmpty(message = "Filter category's name must not be empty")
    private String name;
    private UserBaseInfoDto responsible;
    private List<FilterDto> filters;
    private Boolean deleted;
}
