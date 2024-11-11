package gaiduchek.maksym.api.dto.search;

import gaiduchek.maksym.api.dto.users.SellerDto;
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
public class SearchSellerDto extends BaseSearchDto {

    private List<SellerDto> sellers;
}
