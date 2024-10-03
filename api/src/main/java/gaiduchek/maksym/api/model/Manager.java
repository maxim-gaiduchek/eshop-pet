package gaiduchek.maksym.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "managers")
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
// @AllArgsConstructor // TODO remove
@Getter
@Setter
@SuperBuilder
public class Manager extends User {
}
