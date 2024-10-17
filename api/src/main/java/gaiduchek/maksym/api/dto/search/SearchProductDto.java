package gaiduchek.maksym.api.dto.search;

import gaiduchek.maksym.api.dto.products.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class SearchProductDto extends BaseSearchDto {

    private List<ProductDto> products;
}
