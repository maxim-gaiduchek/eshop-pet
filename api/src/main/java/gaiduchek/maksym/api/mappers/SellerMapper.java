package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.users.SellerDto;
import gaiduchek.maksym.api.model.Seller;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface SellerMapper {

    SellerDto toDto(Seller seller);

    Seller toEntity(SellerDto sellerDto);
}
