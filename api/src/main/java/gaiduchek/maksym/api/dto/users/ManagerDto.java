package gaiduchek.maksym.api.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
// @AllArgsConstructor // TODO remove
@Getter
@Setter
@SuperBuilder
public class ManagerDto extends UserDto {
}
