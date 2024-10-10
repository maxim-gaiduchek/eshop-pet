package gaiduchek.maksym.api.dto.users;

import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
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
public class SellerDto extends UserDto {

    @NotEmpty(message = "Seller address is empty", groups = {CreateGroup.class, UpdateGroup.class})
    private String address;
}
