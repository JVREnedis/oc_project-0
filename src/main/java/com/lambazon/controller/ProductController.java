package com.lambazon.controller;

import com.lambazon.repository.ProductRepository;
import com.lambazon.service.ProductService;
import com.lambazon.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;


@Controller
public class ProductController {
	
	@Inject
	private ProductService productService;
	
	@GetMapping("/products")
	public String products	(Model model) {
		model.addAttribute("prods", productService.products());
		model.addAttribute("totalInventoryAmount", calculateTotalInventoryAmount());
		return "products";
	}
	
	@GetMapping("/products/{id}/details")
	public String product	(@PathVariable Integer id, Model model) {
		model.addAttribute("prod", productService.product(id));
		return "product";
	}
	
	private double calculateTotalInventoryAmount() {
		double TotalInventoryAmount = 0f;
		for (Product p : productService.products()){
			TotalInventoryAmount += p.getInventoryPrice();
		}
		 return TotalInventoryAmount;
	}
}
