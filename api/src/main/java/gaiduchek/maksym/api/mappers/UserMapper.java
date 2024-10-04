package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.UserDto;
import gaiduchek.maksym.api.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto toDto(User user);
}
