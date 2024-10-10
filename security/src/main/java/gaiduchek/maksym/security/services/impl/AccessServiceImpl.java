package gaiduchek.maksym.security.services.impl;

import gaiduchek.maksym.security.services.interfaces.AccessService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("accessService")
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    @Value("${security.api-key}")
    private String apiKey;

    @Override
    public boolean checkTechnicalEndpoint(String apiKey) {
        return Optional.ofNullable(apiKey)
                .filter(key -> StringUtils.equals(key, this.apiKey))
                .isPresent();
    }
}
