package com.product.trial.master.repository.mongo;

import com.product.trial.master.entity.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepository extends MongoRepository<ProductDocument,String> {
}
