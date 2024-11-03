package com.product.trial.master.config;

import com.product.trial.master.repository.Impl.NoSqlProductRepositoryImpl;
import com.product.trial.master.repository.Impl.SqlProductRepositoryImpl;
import com.product.trial.master.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ProductRepository productRepository(
            @Value("${app.datasource.type}") String datasourceType,
            SqlProductRepositoryImpl sqlRepo,
            NoSqlProductRepositoryImpl noSqlRepo) {
        switch (datasourceType) {
            case "sql": return sqlRepo;
            case "nosql": return noSqlRepo;
            default: throw new IllegalArgumentException("Unknown datasource type");
        }
    }
}
