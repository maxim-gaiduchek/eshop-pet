package gaiduchek.maksym.api.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseSearchQueryDto {

    private String createdAtMin;
    private String createdAtMax;
    private Integer page = 1;
    private Integer pageSize = 20;
    private String sortBy = "createdAt";
    private String sortDirection = "desc";
}
