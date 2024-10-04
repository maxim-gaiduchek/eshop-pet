package gaiduchek.maksym.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtRequest {

    @NotEmpty(message = "Login cannot be empty")
    private String login;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
