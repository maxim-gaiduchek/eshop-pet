package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.products.CompanyDto;
import gaiduchek.maksym.api.dto.search.SearchCompanyQueryDto;
import gaiduchek.maksym.api.filters.CompanyFilter;
import gaiduchek.maksym.api.model.Company;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface CompanyMapper {

    CompanyDto toDto(Company company);

    List<CompanyDto> toDtos(List<Company> companies);

    Company toEntity(CompanyDto companyDto);

    CompanyFilter queryToFilter(SearchCompanyQueryDto queryDto);
}
