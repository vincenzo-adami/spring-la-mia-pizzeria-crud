package org.lessons.pizzeria.controller;

import java.util.List;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class PizzasController {

  @Autowired
  private PizzaRepository pizzaRepo;

  @GetMapping
  public String index(Model model) {

    List<Pizza> allPizzas = pizzaRepo.findAll();

    model.addAttribute("pizzas", allPizzas);

    return "index";
  }

}
