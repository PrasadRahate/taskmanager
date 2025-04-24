package com.box.BoxStore.shoebox.repository;

import com.box.BoxStore.shoebox.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


// Extending JpaRepository will give us many methods like saveall, finbyid,findall ...for CRUD operations
public interface ProductRepository extends JpaRepository<Product,Long> {
}
