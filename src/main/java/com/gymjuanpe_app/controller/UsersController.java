package com.gymjuanpe_app.controller;

import com.gymjuanpe_app.model.User;
import com.gymjuanpe_app.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserRepository userRepo;

    public UsersController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("user") User user,
                       BindingResult br,
                       RedirectAttributes ra) {

        if (br.hasErrors()) {
            return "users/form";
        }

        userRepo.save(user);
        ra.addFlashAttribute("ok", "✅ User saved successfully");
        return "redirect:/users";
    }
}

