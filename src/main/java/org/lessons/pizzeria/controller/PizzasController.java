package org.lessons.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

  @GetMapping("/pizzas/create")
  public String create(Model model) {

    model.addAttribute("pizza", new Pizza());

    return "pizzas/create";
  }

  @PostMapping("/pizzas/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      return "pizzas/create";
    }

    pizzaRepo.save(formPizza);

    redirectAttributes.addFlashAttribute("successMsg", "Pizza created");

    return "redirect:/";

  }

}