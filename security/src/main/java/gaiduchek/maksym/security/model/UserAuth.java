package gaiduchek.maksym.security.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "user_auths", schema = "security")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserAuth extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "password")
    private String password;
    @ElementCollection
    @CollectionTable(
            name = "user_rerfresh_tokens", schema = "security",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "user_refresh_token")
    private Set<String> refreshTokens;
}
