package gaiduchek.maksym.api.security.services.interfaces;

import gaiduchek.maksym.api.model.Company;

public interface AccessService {

    boolean checkTechnicalEndpoint(String apiKey);

    boolean isUserThemself(Long id);

    boolean isCustomer();

    boolean isSeller();

    boolean isManager();

    boolean isAdministrator();

    boolean isWorker();

    void checkUserOwnsCompany(Company company);
}
