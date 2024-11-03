package com.product.trial.master.repository.jpa;

import com.product.trial.master.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product,Long> {
}
