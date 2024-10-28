package br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    public static final String USERNAME = "username";

    @Override
    public Optional<String> getCurrentAuditor() {
        Object usernameClaims = null;

        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Jwt jwt) {
            usernameClaims = jwt.getClaims().get(USERNAME);
        }

        return Optional.of(usernameClaims != null ? usernameClaims.toString() : StringUtils.EMPTY);
    }
}
