package com.telecom.link360.controller;

import com.telecom.link360.model.Customer;
import com.telecom.link360.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // LEER (Listar)
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customerList";
    }

    // CREAR (Formulario)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    // GUARDAR (POST)
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        if (customer.getId() == null) {
            // Buscamos el ID máximo actual en la BD y sumamos 1
            Integer maxId = customerRepository.findAll()
                    .stream()
                    .map(Customer::getId)
                    .max(Integer::compare)
                    .orElse(0);
            customer.setId(maxId + 1);
        }
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido: " + id));
        model.addAttribute("customer", customer);
        return "customerForm";
    }

    // BORRAR
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

}