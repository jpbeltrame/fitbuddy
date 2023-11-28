package com.example.fitbuddy.Web;

import com.example.fitbuddy.Entities.*;
import com.example.fitbuddy.Repositories.SubscriptionRepository;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLOutput;


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
        System.out.println(form.name);
        System.out.println(form.username);
        System.out.println(form.password);
        System.out.println(form.birthDate);
        System.out.println(form.subscriptionType);

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


        User user = new User(form.username, form.password,form.name,form.birthDate,form.subscriptionType );
        User userCreated = userRepository.save(user);

        System.out.println(user.getId());
        System.out.println(userCreated.getId());

        Subscription subscription = new Subscription(userCreated.getId(), form.subscriptionType,10);
        subscriptionRepository.save(subscription);
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
    @PostMapping(
            path = "/findABuddy",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String FindBuddyHandler(FindABuddyForm form, Model model) {
        System.out.println(form.trainingDays);
        System.out.println(form.startSession);
        System.out.println(form.endSession);
        //fljvejvln

        String errorField = "";
        String errorMessage = "";

        if (form.trainingDays.isEmpty()) {
            errorField = "trainingDays";
            errorMessage ="Please select at least one training day.";
        }

        if (form.startSession.isEmpty()) {
            errorField = "startSession";
            errorMessage ="Please select the time for start session.";
        }

        if (form.endSession.isEmpty()) {
            errorField = "endSession";
            errorMessage ="Please select the time for end session.";
        }

        if (!errorField.isEmpty()){
            model.addAttribute("page","findABuddy");
            model.addAttribute("errorField",errorField);
            model.addAttribute("errorMessage",errorMessage);
            return "app/findABuddy";
        }

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