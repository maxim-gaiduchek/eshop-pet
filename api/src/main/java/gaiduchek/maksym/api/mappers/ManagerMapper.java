package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.users.ManagerDto;
import gaiduchek.maksym.api.model.Manager;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ManagerMapper {

    ManagerDto toDto(Manager manager);

    Manager toEntity(ManagerDto managerDto);
}
