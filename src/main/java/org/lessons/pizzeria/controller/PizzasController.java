package org.lessons.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/")
public class PizzasController {

  @Autowired
  private PizzaRepository pizzaRepo;

  @GetMapping
  public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

    List<Pizza> allPizzas;

    if (keyword != null && !keyword.isBlank()) {
      allPizzas = pizzaRepo.findByNameContaining(keyword);
      model.addAttribute("keyword", keyword);
    } else {
      allPizzas = pizzaRepo.findAll();
    }

    model.addAttribute("pizzas", allPizzas);

    return "index";
  }

  @GetMapping("/pizzas/show/{id}")
  public String show(@PathVariable(name = "id") Long id,
      @RequestParam(name = "keyword", required = false) String keyword, Model model) {

    Optional<Pizza> pizzaOptional = pizzaRepo.findById(id);

    if (pizzaOptional.isPresent()) {
      model.addAttribute("pizza", pizzaOptional.get());
    }
    model.addAttribute("keyword", keyword);
    if (keyword == null || keyword.isBlank() || keyword.equals("null")) {
      model.addAttribute("pizzaUrl", "/");
    } else {
      model.addAttribute("pizzaUrl", "/?keyword=" + keyword);
    }

    return "pizzas/show";
  }

}