package gaiduchek.maksym.api.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SearchProductQueryDto extends BaseSearchQueryDto {

    private String name;
    private BigDecimal costMin;
    private BigDecimal costMax;
    private List<Integer> companyIds;
    private List<Integer> productCategoryIds;
    private List<Integer> filterIds;
    private List<Boolean> deleted;
}
