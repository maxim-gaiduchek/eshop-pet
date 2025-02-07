package gaiduchek.maksym.api.dto.products;

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
public class ImageDto {

    @NotNull(message = "Image's id must not be null", groups = UsedInOtherGroup.class)
    private Long id;
}
