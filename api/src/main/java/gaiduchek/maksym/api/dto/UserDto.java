package gaiduchek.maksym.api.dto;

import gaiduchek.maksym.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserDto {

    protected Long id;
    protected String name;
    protected String surname;
    protected String email;
    protected String phone;
    protected Role role;
}
