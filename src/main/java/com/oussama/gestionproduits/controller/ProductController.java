package com.oussama.gestionproduits.controller;

import com.oussama.gestionproduits.entity.Product;
import com.oussama.gestionproduits.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProducts(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isBlank()) {
            model.addAttribute("products", productRepository.findByNameContainingIgnoreCase(keyword.trim()));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("keyword", "");
        }
        return "products";
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Ajouter un produit");
        model.addAttribute("formAction", "/products");
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", product.getId() == null ? "Ajouter un produit" : "Modifier un produit");
            model.addAttribute("formAction", product.getId() == null ? "/products" : "/products/" + product.getId());
            return "product-form";
        }
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("successMessage", "Produit enregistré avec succès.");
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return productRepository.findById(id).map(product -> {
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Modifier un produit");
            model.addAttribute("formAction", "/products");
            return "product-form";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("errorMessage", "Produit introuvable.");
            return "redirect:/products";
        });
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Produit supprimé.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Produit introuvable.");
        }
        return "redirect:/products";
    }
}
