package com.example.fitbuddy.Web;

import com.example.fitbuddy.Entities.SignupForm;
import com.example.fitbuddy.Entities.Subscription;
import com.example.fitbuddy.Entities.User;
import com.example.fitbuddy.Repositories.SubscriptionRepository;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepository;
    SubscriptionRepository subscriptionRepository;

    @GetMapping(path = "/signUp")
    public String IndexPageLoader(Model model) {
        model.addAttribute("page", "createAccount");
        return "app/createAccount";
    }

    @PostMapping(
            path = "/signUp",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String CreateUserHandler(SignupForm form, Model model) {
        String errorField = "";
        String errorMessage = "";


        if (form.name.isEmpty()) {
            errorField = "name";
           errorMessage ="Please insert your name.";
        }
        if (form.password.length() < 6) {
            errorField = "password";
            errorMessage ="Please enter a password with more than 6 characters";
        }
        if (!form.username.contains("@")) {
            errorField = "username";
            errorMessage = "Please enter a valid e-mail.";
        }
        if (!form.subscriptionType.equals("personalTrainer") && !form.subscriptionType.equals("buddy")) {
            errorField = "Subscription Type";
            errorMessage =  "Please don't touch our html";
        }

        if (!errorField.isEmpty()){
            model.addAttribute("page","createAccount");
            model.addAttribute("errorField",errorField);
            model.addAttribute("errorMessage",errorMessage);
            return "app/createAccount";
        }

        User user = userRepository.save(new User(form.username, form.password,form.name,form.birthDate,form.subscriptionType ));
        subscriptionRepository.save( new Subscription(user.getId(), form.subscriptionType,10));

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