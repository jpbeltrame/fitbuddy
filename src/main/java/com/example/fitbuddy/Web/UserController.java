package com.example.fitbuddy.Web;

import com.example.fitbuddy.DTO.SignupForm;
import com.example.fitbuddy.DTO.FindABuddyForm;
import com.example.fitbuddy.Entities.Subscription;
import com.example.fitbuddy.Entities.UserFitbuddy;
import com.example.fitbuddy.Entities.UserPreferences;
import com.example.fitbuddy.Repositories.SubscriptionRepository;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "app/signUp")
    public String IndexPageLoader(Model model) {
        model.addAttribute("page", "createAccount");
        return "app/createAccount";
    }

    @PostMapping(
            path = "app/signUp",
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
        if (form.city.isEmpty()) {
            errorField = "city";
            errorMessage ="Please insert your city.";
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

        String password = passwordEncoder.encode(form.password);

        UserFitbuddy userFitbuddy = new UserFitbuddy();
        userFitbuddy.setName(form.name);
        userFitbuddy.setPassword(password);
        userFitbuddy.setCity(form.city);
        userFitbuddy.setBirthDate(form.birthDate);
        userFitbuddy.setSubscriptionType(form.subscriptionType);
        userFitbuddy.setUsername(form.username);
        userFitbuddy = userRepository.save(userFitbuddy);
        subscriptionRepository.save( new Subscription(userFitbuddy.getId(), form.subscriptionType,10));

        return "app/index";
    }

    @GetMapping(path = "app/dashboard")
    public String DashboardPageLoader(Model model, Authentication authentication) {
        UserFitbuddy user = userRepository.findUserByEmail(
                authentication.getName()
        );

        model.addAttribute("page", "dashboard");
        model.addAttribute("user", user);

        return "app/index";
    }

    @GetMapping(path = "app/findABuddy")
    public String FindABuddyPageLoader(Model model) {
        model.addAttribute("page", "findABuddy");
        return "app/findABuddy";
    }

    @PostMapping(
            path = "app/findABuddy",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String FindBuddyHandler(FindABuddyForm form, Model model) {

        System.out.println("---------Trainning days: " + Arrays.toString(form.getTrainingDays()));
        System.out.println("---------Start session: " + form.getStartSession());
        System.out.println("---------End session: " + form.getEndSession());


        String errorField = "";
        String errorMessage = "";

        List<UserFitbuddy> users = userRepository.findUserByGender(form.getGender());
        model.addAttribute("users", users);

        for (UserFitbuddy u: users) {
            System.out.println(u.getName());
        }

        System.out.println("---------Size: " + users.size());

        if (form.getTrainingDays().length == 0) {
            errorField = "trainingDays";
            errorMessage ="Please select at least one training day.";
        }

        return "app/findABuddy";
    }


    @PostMapping(
            path = "app/updatePrefereces",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String SavePreferencesHandler(Model model, FindABuddyForm form, Authentication authentication) {

        UserFitbuddy user = userRepository.findUserByEmail(authentication.getName());

        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setGender(form.getGender());
        userPreferences.setTrainingDays(form.getTrainingDays());
        userPreferences.setTrainingObjective(form.getTrainingObjective());
        userPreferences.setStartSession(form.getStartSession());
        userPreferences.setEndSession(form.getEndSession());

        user.setPreferences(userPreferences);

        userRepository.save(user);

        return "redirect:/app/dashboard";
    };

    @GetMapping(path = "app/forgotPassword")
    public String ForgotPasswordPageLoader(Model model) {
        model.addAttribute("page", "forgotPassword");
        return "app/forgotPassword";
    }

    @GetMapping(path = "app/login")
    public String LoginPageLoader(Model model) {
        model.addAttribute("page", "login");
        return "app/login";
    }

    @GetMapping(path = "app/profile")
    public String ProfilePageLoader(Model model, Authentication authentication) {
        model.addAttribute("page", "profile");
        UserFitbuddy user = userRepository.findUserByEmail(
                authentication.getName()
        );

        model.addAttribute("page","profile");
        model.addAttribute("user", user);

        return "app/profile";
    }

    @GetMapping(path = "app/settings")
    public String SettingsPageLoader(Model model) {
        model.addAttribute("page", "settings");
        return "app/profile";
    }
}