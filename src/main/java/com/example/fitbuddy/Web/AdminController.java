package com.example.fitbuddy.Web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {

    @GetMapping(path = "admin")
    public String adminPageLoader(Model model) {
        model.addAttribute("page", "adminPage");
        return "app/adminPage";
    }

//    @PostMapping(path = "admin")
//    public String adminPageLoader(Model model) {
//        model.addAttribute("page", "adminPage");
//        return "app/adminPage";
//    }
}
