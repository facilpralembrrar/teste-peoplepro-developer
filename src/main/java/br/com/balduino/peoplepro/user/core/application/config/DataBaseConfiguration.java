package br.com.balduino.peoplepro.user.core.application.config;

import br.com.balduino.peoplepro.user.core.infrastructure.adapters.repository.utils.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableEnversRepositories(basePackages = "br.com.balduino.peoplepro.user.core.infrastructure.adapters")
public class DataBaseConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
