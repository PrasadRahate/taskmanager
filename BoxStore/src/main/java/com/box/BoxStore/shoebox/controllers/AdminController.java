package com.box.BoxStore.shoebox.controllers;


import com.box.BoxStore.shoebox.model.Product;
import com.box.BoxStore.shoebox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository ;

//    //Show admin dashboard
//    @GetMapping("/dashboard")
//    public String showadmindashboard(){
//        return "admin-dashboard";
//    }




}
