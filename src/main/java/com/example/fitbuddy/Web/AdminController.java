package com.example.fitbuddy.Web;

import com.example.fitbuddy.DTO.SearchForm;
import com.example.fitbuddy.Entities.UserFitbuddy;
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

    @GetMapping(path = "admin")
    public String adminPageLoader(Model model) {
        model.addAttribute("page", "adminPage");
        return "app/adminPage";
    }

    @PostMapping(path = "/admin/user",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String adminUserSearchLoader(Model model, SearchForm searchForm) {
        model.addAttribute("page", "adminPage");
        model.addAttribute("searchString", searchForm.getSearch());

        List<UserFitbuddy> users = userRepository.searchByUserName(searchForm.getSearch());

        model.addAttribute("users", users);

        System.out.println(users.size());
        System.out.println(searchForm.getSearch());


        return "app/adminPage";
    }
}
