package com.example.fitbuddy.Web;

import com.example.fitbuddy.DTO.SignupForm;
import com.example.fitbuddy.DTO.FindABuddyForm;
import com.example.fitbuddy.DTO.UserConnectForm;
import com.example.fitbuddy.Entities.*;
import com.example.fitbuddy.Repositories.BuddyRepository;
import com.example.fitbuddy.Repositories.PersonalTrainerRepository;
import com.example.fitbuddy.Repositories.SubscriptionRepository;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private PasswordEncoder passwordEncoder;
    private BuddyRepository buddyRepository;
    private PersonalTrainerRepository personalTrainerRepository;

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

        UserFitbuddy user = userRepository.findUserByEmail(form.username);
        if (user != null) {
            errorField = "username";
            errorMessage ="An account with this a-mail already exists.";
        }

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

        double price = 0;

        if (form.subscriptionType.equals("buddy")) {
            Buddy buddy = new Buddy();
            buddy.setUserId(userFitbuddy.getId());
            buddyRepository.save(buddy);
            price = 1;
        } else {
            PersonalTrainer personal = new PersonalTrainer();
            personal.setUserId(userFitbuddy.getId());
            personalTrainerRepository.save(personal);
            price = 10;
        }
        subscriptionRepository.save(
                new Subscription(userFitbuddy.getId(), form.subscriptionType, price)
        );

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
        String errorField = "";
        String errorMessage = "";

        List<UserFitbuddy> users = userRepository.findUserByGender(
                form.getGender(),
                form.getTrainingObjective()
        );
        model.addAttribute("users", users);

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

    @PostMapping(
            path = "app/userConnect",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String ConnectUser(UserConnectForm form, Authentication authentication) {
        UserFitbuddy friendUser = userRepository.findById(form.getUserId()).get();
        UserFitbuddy user = userRepository.findUserByEmail(authentication.getName());

        Buddy userBuddy = buddyRepository.findByUserID(user.getId());
        Buddy friendBuddy = buddyRepository.findByUserID(friendUser.getId());

        BuddyConnectionRequest requester = new BuddyConnectionRequest(
                friendUser.getId(),
                true
        );

        BuddyConnectionRequest friend =new BuddyConnectionRequest();
        friend.setUserId(user.getId());

        List<BuddyConnectionRequest> buddies = userBuddy.getBuddiesIds();
        if (buddies == null) {
            buddies = new ArrayList<BuddyConnectionRequest>();
        }

        if(!buddies.contains(requester))
            buddies.add(requester);

        userBuddy.setBuddiesIds(buddies);
        buddyRepository.save(userBuddy);


        // friend
        buddies = friendBuddy.getBuddiesIds();
        if (buddies == null) {
            buddies = new ArrayList<BuddyConnectionRequest>();
        }
        if(!buddies.contains(friend))
            buddies.add(friend);

        friendBuddy.setBuddiesIds(buddies);
        buddyRepository.save(friendBuddy);

        return "redirect:/app/findABuddy?connected=true";
    };

    @PostMapping(
            path = "app/userConnect/{id}/accept",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String AllowConnection(UserConnectForm form, Authentication authentication) {

        UserFitbuddy user = userRepository.findUserByEmail(authentication.getName());
        Buddy userBuddy = buddyRepository.findByUserID(user.getId());

        userBuddy.getBuddiesIds().forEach(u -> {
            if(u.getUserId().equals(form.getUserId()))
                u.setApproved(true);
        });

        buddyRepository.save(userBuddy);

        return "redirect:/app/profile?connected=true";
    };

    @PostMapping(
            path = "app/userConnect/{id}/block",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String block(UserConnectForm form, Authentication authentication) {

        UserFitbuddy user = userRepository.findUserByEmail(authentication.getName());
        Buddy userBuddy = buddyRepository.findByUserID(user.getId());

        userBuddy.getBuddiesIds().forEach(u -> {
            if(u.getUserId().equals(form.getUserId()))
                u.setApproved(false);
        });

        buddyRepository.save(userBuddy);

        return "redirect:/app/profile?connected=true";
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
        UserFitbuddy user = userRepository.findUserByEmail(
                authentication.getName()
        );

        Subscription subscription = subscriptionRepository.findUserById(
                user.getId()
        );

        Buddy buddy = buddyRepository.findByUserID(user.getId());
        List<String> usersIDs = buddy.getBuddiesIds().stream().map(BuddyConnectionRequest::getUserId).toList();
        List<UserFitbuddy> friends = userRepository.getByUserIds(usersIDs);

        ArrayList<String> PendingUsersIDs = new ArrayList<>();
        for (BuddyConnectionRequest b : buddy.getBuddiesIds()) {
            if (b.getApproved() == null) {
                PendingUsersIDs.add(b.getUserId());
            }
        }

        model.addAttribute("page","profile");
        model.addAttribute("user", user);
        model.addAttribute("buddies", friends);
        model.addAttribute("subscription", subscription);

        return "app/profile";
    }
}