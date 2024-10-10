package gaiduchek.maksym.api.dto.users;

import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "User name is empty", groups = {CreateGroup.class, UpdateGroup.class})
    protected String name;
    @NotEmpty(message = "User surname is empty", groups = {CreateGroup.class, UpdateGroup.class})
    protected String surname;
    @NotEmpty(message = "User email is empty", groups = {CreateGroup.class, UpdateGroup.class})
    @Email(message = "User email is invalid", groups = {CreateGroup.class, UpdateGroup.class})
    protected String email;
    @NotEmpty(message = "User name is empty", groups = {CreateGroup.class, UpdateGroup.class})
    protected String phone;
    protected Role role;
    @NotEmpty(message = "User password is empty", groups = {CreateGroup.class, UpdateGroup.class})
    protected String password;
}
