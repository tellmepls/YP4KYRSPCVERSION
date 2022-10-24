package com.example.Vlarionov.controlers;
import com.example.Vlarionov.Models.Role;
import com.example.Vlarionov.Models.furniture;
import com.example.Vlarionov.Repositories.furnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private furnitureRepository furnitureRepository;

    @GetMapping("/registration")
    public String reg(furniture furniture) {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(furniture furniture,
                      Model model) {

        if(furnitureRepository.findByusername(furniture.getUsername()) != null) {
            model.addAttribute("error", "Логин занят!");
            return ("registration");
        }

        furniture.setActive(true);
        furniture.setRoles(Collections.singleton(Role.USER));

        furnitureRepository.save(furniture);

        return ("redirect:/login");
    }
}
