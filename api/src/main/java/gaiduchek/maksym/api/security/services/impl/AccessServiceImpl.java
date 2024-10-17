package gaiduchek.maksym.api.security.services.impl;

import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.CompanyExceptionCodes;
import gaiduchek.maksym.api.model.Company;
import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.security.services.interfaces.AccessService;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service("accessService")
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    @Value("${security.api-key}")
    private String apiKey;

    private final SecurityProvider securityProvider;

    @Override
    public boolean checkTechnicalEndpoint(String apiKey) {
        return Optional.ofNullable(apiKey)
                .filter(key -> StringUtils.equals(key, this.apiKey))
                .isPresent();
    }

    @Override
    public boolean isUserThemself(Long id) {
        var userId = securityProvider.fetchUserId();
        return Objects.equals(id, userId);
    }

    @Override
    public boolean isCustomer() {
        var auth = securityProvider.fetchAuthentication();
        return auth != null && auth.hasRole(Role.ROLE_CUSTOMER);
    }

    @Override
    public boolean isSeller() {
        var auth = securityProvider.fetchAuthentication();
        return auth != null && auth.hasRole(Role.ROLE_SELLER);
    }

    @Override
    public boolean isManager() {
        var auth = securityProvider.fetchAuthentication();
        return auth != null && auth.hasRole(Role.ROLE_MANAGER);
    }

    @Override
    public boolean isAdministrator() {
        var auth = securityProvider.fetchAuthentication();
        return auth != null && auth.hasRole(Role.ROLE_ADMINISTRATOR);
    }

    @Override
    public boolean isWorker() {
        return isManager() || isAdministrator();
    }

    @Override
    public void checkUserOwnsCompany(Company company) {
        var userId = securityProvider.fetchUserId();
        if (Objects.equals(company.getSeller().getId(), userId)) {
            throw new ValidationException(CompanyExceptionCodes.COMPANY_IS_NOT_OWNED_BY_SELLER, company.getId());
        }
    }
}
