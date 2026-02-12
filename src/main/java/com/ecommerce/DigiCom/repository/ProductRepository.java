package com.ecommerce.DigiCom.repository;

import com.ecommerce.DigiCom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
            "   OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
            "   OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :searchwords, '%')) " +
            "   OR LOWER(p.category) LIKE LOWER(CONCAT('%', :searchwords, '%'))"+
    "   OR LOWER(p.imageName) LIKE LOWER(CONCAT('%', :searchwords, '%'))")
    List<Product> searchProduct(String searchwords);
}
