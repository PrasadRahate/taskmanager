package com.box.BoxStore.shoebox.controllers;


import com.box.BoxStore.shoebox.model.Product;
import com.box.BoxStore.shoebox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductViewController {


    @Autowired
    private ProductRepository productRepository;

    //Show add products
    @GetMapping("/products")
    public String getProductPage(Model themodel) {
        List<Product> productList = productRepository.findAll();
        themodel.addAttribute("products", productList);
        return "products";
    }


    //Show Add product form
    @GetMapping("/products/add")
    public String showaddProductForm(Model model) {
        model.addAttribute("product",new Product());
        return "add-product" ;
    }

    // handle Product Submission
    @PostMapping("/products")
    public String addProduct(@ModelAttribute("product")Product product) {
        productRepository.save(product);
        return "redirect:/products" ; //redirects to product list
    }

    // to edit products page
    @GetMapping("/products/edit/{id}")
    public String editProducts(@PathVariable long id, Model model){

        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id : " + id));
        model.addAttribute("product",product);
        return "edit-products" ;  // to editing page
    }

    @PutMapping("/products/update/{id}")
    public String updateProducts(@PathVariable long id,@ModelAttribute Product updatedproduct){
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        product.setName(updatedproduct.getName());
        product.setDescription(updatedproduct.getDescription());
        product.setPrice(updatedproduct.getPrice());
        productRepository.save(product);
        return "redirect:/products" ; //redirecting to products listing page
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productRepository.deleteById(id);
        return "redirect:/products" ;
    }

}
