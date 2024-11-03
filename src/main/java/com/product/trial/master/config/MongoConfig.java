package com.product.trial.master.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.product.trial.master.repository.mongo")
public class MongoConfig {
}
