package com.box.BoxStore.shoebox.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Make sure login.html exists in templates
    }

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin-dashboard"; // Should map to admin-dashboard.html
    }
}
