package gaiduchek.maksym.api.dto;

import gaiduchek.maksym.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role;
}
