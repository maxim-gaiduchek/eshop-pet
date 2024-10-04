package gaiduchek.maksym.security.dto;

import gaiduchek.maksym.security.model.Role;
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
public class JwtResponse {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private Role role;
}
