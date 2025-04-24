package com.box.BoxStore;

import com.box.BoxStore.shoebox.model.Product;
import com.box.BoxStore.shoebox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoxStoreApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository ;
	public static void main(String[] args) {
		SpringApplication.run(BoxStoreApplication.class, args);
	}



	// This method to simply add a new product in database table
	@Override
	public void run(String... args) throws Exception{
		// Test: Save a product
		Product product = new Product() ;
		product.setName("Nike shoe Box Store");
		product.setDescription("A great shoe rack to organise your shoes.");
		product.setPrice(1299);

		// adding this product to table using save method provided by JPArepo
//		productRepository.save(product);
		System.out.println("Product saved Successfully");
	}
}
