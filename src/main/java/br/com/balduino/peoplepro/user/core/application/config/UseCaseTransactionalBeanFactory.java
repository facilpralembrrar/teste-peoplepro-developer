package br.com.balduino.peoplepro.user.core.application.config;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import java.util.Properties;

public final class UseCaseTransactionalBeanFactory {
    private static UseCaseTransactionalBeanFactory instance;

    private UseCaseTransactionalBeanFactory() {
    }

    public static UseCaseTransactionalBeanFactory getInstance() {
        if (instance == null) {
            instance = new UseCaseTransactionalBeanFactory();
        }
        return instance;
    }

    public Object newTransactionalUseCase(Object userCase, PlatformTransactionManager transactionManager) {
        Properties transactionAttributes = new Properties();
        transactionAttributes.put("*", "PROPAGATION_REQUIRED");

        return newTransactionalUseCase(userCase, transactionManager, transactionAttributes);
    }

    public Object newTransactionalReadOnlyUseCase(Object userCase, PlatformTransactionManager transactionManager) {
        Properties transactionAttributes = new Properties();
        transactionAttributes.put("*", "PROPAGATION_REQUIRED,readOnly");

        return newTransactionalUseCase(userCase, transactionManager, transactionAttributes);
    }

    private Object newTransactionalUseCase(Object userCase,
                                          PlatformTransactionManager transactionManager,
                                          Properties transactionAttributes) {

        TransactionProxyFactoryBean proxy = new TransactionProxyFactoryBean();
        proxy.setTransactionManager(transactionManager);
        proxy.setTarget(userCase);
        proxy.setTransactionAttributes(transactionAttributes);
        proxy.afterPropertiesSet();

        return proxy.getObject();
    }
}
