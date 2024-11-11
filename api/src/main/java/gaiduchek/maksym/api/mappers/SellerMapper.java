package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.search.SearchSellerQueryDto;
import gaiduchek.maksym.api.dto.users.SellerDto;
import gaiduchek.maksym.api.filters.SellerFilter;
import gaiduchek.maksym.api.model.Seller;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface SellerMapper {

    SellerDto toDto(Seller seller);

    List<SellerDto> toDtos(List<Seller> sellers);

    Seller toEntity(SellerDto sellerDto);

    SellerFilter queryToFilter(SearchSellerQueryDto queryDto);
}
