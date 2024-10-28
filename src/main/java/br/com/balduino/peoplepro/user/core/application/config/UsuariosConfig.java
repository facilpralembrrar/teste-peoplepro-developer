package br.com.balduino.peoplepro.user.core.application.config;

import br.com.balduino.peoplepro.user.core.domain.ports.inbound.*;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.QueryUsuarioOutputPort;
import br.com.balduino.peoplepro.user.core.domain.ports.outbound.SaveUsuarioOutputPort;
import br.com.balduino.peoplepro.user.core.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UsuariosConfig {

    @Bean
    public GetUsuarioByIdUseCase getUsuarioByIdUseCase(
            @Autowired PlatformTransactionManager platformTransactionManager,
            @Autowired QueryUsuarioOutputPort queryUsuarioOutputPort) {
        GetUsuarioByIdUseCase getUsuarioByIdUseCase = new GetUsuarioByIdService(queryUsuarioOutputPort);

        return (GetUsuarioByIdUseCase) UseCaseTransactionalBeanFactory.getInstance().newTransactionalReadOnlyUseCase(
                getUsuarioByIdUseCase,
                platformTransactionManager);
    }

    @Bean
    public GetAllUsuariosUseCase getAllPlansUseCase(
            @Autowired PlatformTransactionManager platformTransactionManager,
            @Autowired QueryUsuarioOutputPort queryUsuarioOutputPort) {
        GetAllUsuariosUseCase getAllUsuariosService = new GetAllUsuariosService(queryUsuarioOutputPort);

        return (GetAllUsuariosUseCase) UseCaseTransactionalBeanFactory.getInstance().newTransactionalReadOnlyUseCase(
                getAllUsuariosService,
                platformTransactionManager);
    }

    @Bean
    public CreateUsuarioUseCase createUsuarioUseCase(
            @Autowired PlatformTransactionManager platformTransactionManager,
            @Autowired SaveUsuarioOutputPort saveUsuarioOutputPort) {
        CreateUsuarioUseCase createUsuarioUseCase = new CreateUsuarioService(saveUsuarioOutputPort);

        return (CreateUsuarioUseCase) UseCaseTransactionalBeanFactory.getInstance().newTransactionalUseCase(
                createUsuarioUseCase,
                platformTransactionManager);
    }

    @Bean
    public UpdateUsuarioUseCase updateUsuarioUseCase(
            @Autowired PlatformTransactionManager platformTransactionManager,
            @Autowired QueryUsuarioOutputPort queryUsuarioOutputPort,
            @Autowired SaveUsuarioOutputPort saveUsuarioOutputPort) {
        UpdateUsuarioUseCase updateUsuarioUseCase = new UpdateUsuarioService(queryUsuarioOutputPort, saveUsuarioOutputPort);

        return (UpdateUsuarioUseCase) UseCaseTransactionalBeanFactory.getInstance().newTransactionalUseCase(
                updateUsuarioUseCase,
                platformTransactionManager);
    }

    @Bean
    public DeleteUsuarioUseCase deleteUsuarioUseCase(
            @Autowired PlatformTransactionManager platformTransactionManager,
            @Autowired QueryUsuarioOutputPort queryUsuarioOutputPort,
            @Autowired SaveUsuarioOutputPort saveUsuarioOutputPort) {
        DeleteUsuarioUseCase deleteUsuarioUseCase = new DeleteUsuarioService(queryUsuarioOutputPort, saveUsuarioOutputPort);

        return (DeleteUsuarioUseCase) UseCaseTransactionalBeanFactory.getInstance().newTransactionalUseCase(
                deleteUsuarioUseCase,
                platformTransactionManager);
    }
}
