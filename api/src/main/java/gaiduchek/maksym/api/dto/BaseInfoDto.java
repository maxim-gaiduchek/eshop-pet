package gaiduchek.maksym.api.dto;

import gaiduchek.maksym.api.validation.groups.UsedInOtherGroup;
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
public class BaseInfoDto {

    @NotNull(message = "Id must not be null", groups = UsedInOtherGroup.class)
    private Long id;
    private String name;
}
