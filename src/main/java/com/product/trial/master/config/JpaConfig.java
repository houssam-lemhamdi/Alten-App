package com.product.trial.master.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.product.trial.master.repository.jpa")
public class JpaConfig {
}
