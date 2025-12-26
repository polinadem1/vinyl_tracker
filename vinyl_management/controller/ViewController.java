package com.vinyl.vinyl_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    @GetMapping("/index.html")
    public String indexPage() {
        return "index.html";
    }
    
    @GetMapping("/login.html")
    public String loginPage() {
        return "login.html";
    }
    
    @GetMapping("/register.html")
    public String registerPage() {
        return "register.html";
    }
    
    @GetMapping("/albums.html")
    public String albumsPage() {
        return "albums.html";
    }
    
    @GetMapping("/artists.html")
    public String artistsPage() {
        return "artists.html";
    }
    
    @GetMapping("/collection.html")
    public String collectionPage() {
        return "collection.html";
    }
    
    @GetMapping("/wishlist.html")
    public String wishlistPage() {
        return "wishlist.html";
    }
}