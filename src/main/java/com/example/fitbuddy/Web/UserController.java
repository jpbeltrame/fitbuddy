package com.example.fitbuddy.Web;

import com.example.fitbuddy.Entities.User;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/signUp")
    public String IndexPageLoader(Model model) {
        model.addAttribute("page", "createAccount");
        return "app/createAccount";
    }

    @PostMapping(
            path="/signUp",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )

    public String CreateUserHandler(User user) {
        System.out.println("teste");

        userRepository.save(user);

        return "app/index";
    }

    @GetMapping(path = "/dashboard")
    public String DashboardPageLoader(Model model) {
        model.addAttribute("page", "dashboard");
        return "app/index";
    }
    @GetMapping(path = "/findABuddy")
    public String FindABuddyPageLoader(Model model) {
        model.addAttribute("page", "findABuddy");
        return "app/findABuddy";
    }
    @GetMapping(path = "/forgotPassword")
    public String ForgotPasswordPageLoader(Model model) {
        model.addAttribute("page", "forgotPassword");
        return "app/forgotPassword";
    }
    @GetMapping(path = "/login")
    public String LoginPageLoader(Model model) {
        model.addAttribute("page", "login");
        return "app/login";
    }
    @GetMapping(path = "/profile")
    public String ProfilePageLoader(Model model) {
        model.addAttribute("page", "profile");
        return "app/profile";
    }

    @GetMapping(path = "/settings")
    public String SettingsPageLoader(Model model) {
        model.addAttribute("page", "settings");
        return "app/profile";
    }

}