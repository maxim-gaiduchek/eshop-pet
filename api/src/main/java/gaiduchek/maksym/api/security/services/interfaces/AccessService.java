package gaiduchek.maksym.api.security.services.interfaces;

public interface AccessService {

    boolean checkTechnicalEndpoint(String apiKey);

    boolean isUserThemself(Long id);

    boolean isCustomer();

    boolean isSeller();

    boolean isManager();

    boolean isAdministrator();

    boolean isWorker();
}
