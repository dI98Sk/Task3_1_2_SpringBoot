package com.skakundima.spring.boot.KataTask_3_1_2.controllers;


import com.skakundima.spring.boot.KataTask_3_1_2.models.User;
import com.skakundima.spring.boot.KataTask_3_1_2.services.UsersService;
import com.skakundima.spring.boot.KataTask_3_1_2.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/")
    public String users(@ModelAttribute("user") User user, Model model, Long id) {


        model.addAttribute("users", usersService.findAll());

        model.addAttribute("user", new User());

        return "users";
    }


    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user) {

        usersService.save(user);

        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {

        usersService.delete(id);

        return "redirect:/";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {

        usersService.update(id, user);
        return "redirect:/";
    }
}
