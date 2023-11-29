package com.example.fitbuddy.Web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SiteController {

    @GetMapping(path = "/")
    public String defaultPageLoader() {
        return "redirect:site/";
    }
    @GetMapping(path = "site/")
    public String indexPageLoader(Model model) {
        model.addAttribute("page", "main");
        return "site/main";
    }

    @GetMapping(path = "site/about")
    public String aboutPageLoader(Model model) {
        model.addAttribute("page", "about");
        return "site/about";
    }

    @GetMapping(path = "site/support")
    public String supportPageLoader(Model model) {
        model.addAttribute("page", "support");
        return "site/support";
    }

    @GetMapping(path = "site/eula")
    public String EULAPageLoader(Model model) {
        model.addAttribute("page", "eula");
        return "site/eula";
    }
}
