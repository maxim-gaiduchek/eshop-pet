package gaiduchek.maksym.api.dto.products;

import gaiduchek.maksym.api.dto.users.UserBaseInfoDto;
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
public class CompanyDto {

    @NotNull(message = "Company id must not be null", groups = {UsedInOtherGroup.class})
    private Long id;
    @NotEmpty(message = "Company name must not be empty", groups = {CreateGroup.class, UpdateGroup.class})
    private String name;
    private UserBaseInfoDto seller;
}
