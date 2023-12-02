package com.example.fitbuddy.Web;

import com.example.fitbuddy.DTO.BlockUserForm;
import com.example.fitbuddy.DTO.SearchForm;
import com.example.fitbuddy.Entities.Subscription;
import com.example.fitbuddy.Entities.UserFitbuddy;
import com.example.fitbuddy.Repositories.SubscriptionRepository;
import com.example.fitbuddy.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    UserRepository userRepository;
    SubscriptionRepository subscriptionRepository;

    @GetMapping(path = "admin")
    public String adminPageLoader(Model model) {
        model.addAttribute("page", "adminPage");

        List<Subscription> buddy = subscriptionRepository.getByType("buddy");
        List<Subscription> personal = subscriptionRepository.getByType("personal");

        int buddySum = buddy.size();
        int personalSum = personal.size();
        int sum = buddySum + personalSum;
        int personalRevenue = personalSum * 10;

        model.addAttribute("buddy", buddySum);
        model.addAttribute("personal", personalSum);
        model.addAttribute("totalSubs", sum);

        int buddiesRevenue = buddySum + personalRevenue;

        model.addAttribute("totalRevenue", buddiesRevenue);
        model.addAttribute("personalRevenue", personalRevenue);


        return "app/adminPage";
    }

    @PostMapping(path = "/admin/user",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String adminUserSearchLoader(Model model, SearchForm searchForm) {
        model.addAttribute("page", "adminPage");
        model.addAttribute("searchString", searchForm.getSearch());

        List<UserFitbuddy> users = userRepository.searchByUserName(searchForm.getSearch());

        model.addAttribute("users", users);

        List<Subscription> buddy = subscriptionRepository.getByType("buddy");
        List<Subscription> personal = subscriptionRepository.getByType("personal");

        int buddySum = buddy.size();
        int personalSum = personal.size();
        int sum = buddySum + personalSum;
        int personalRevenue = personalSum * 10;

        model.addAttribute("buddy", buddySum);
        model.addAttribute("personal", personalSum);
        model.addAttribute("totalSubs", sum);

        int buddiesRevenue = buddySum + personalRevenue;

        model.addAttribute("totalRevenue", buddiesRevenue);
        model.addAttribute("personalRevenue", personalRevenue);


        return "app/adminPage";
    }

    @PostMapping(path = "/admin/user/toggleStatus",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String adminBlockUser(BlockUserForm blockUserForm){

        UserFitbuddy userToBlock = userRepository.findUserByEmailIgnoreStatus(blockUserForm.getEmail());

        if(userToBlock.getStatus().equals("active")){
            userToBlock.setStatus("inactive");
        } else{
            userToBlock.setStatus("active");
        }

        userRepository.save(userToBlock);

        return "redirect:/admin";

    }
}
