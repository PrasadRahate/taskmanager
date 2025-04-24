package com.box.BoxStore.shoebox.controllers;


import com.box.BoxStore.shoebox.model.Product;
import com.box.BoxStore.shoebox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository ;



    //List down all products
    @GetMapping
    public List<Product> getAllProducts(){

        return productRepository.findAll();
    }

    // get Product by id passing in endpoint and mapping it to method using @Pathvariable
    @GetMapping("/{id}")
    public Optional<Product> getProductbyID(@PathVariable long id){

        return productRepository.findById(id);
    }

    // Create a product
    @PostMapping
    public Product createProduct(@RequestBody Product product){

         return productRepository.save(product);
    }


    //Update a product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id,@RequestBody Product productDetails){

        Product product = productRepository.findById(id).orElseThrow() ;
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);

    }


    // Delete a Product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
        productRepository.deleteById(id);
    }

}
