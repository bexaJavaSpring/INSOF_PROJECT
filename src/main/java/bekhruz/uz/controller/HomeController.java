package bekhruz.uz.controller;

import bekhruz.uz.dtos.UserLoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "register";
    }

    @GetMapping("/back")
    public String back() {
        return "generate-code";
    }

    @PostMapping("/click-count")
    public String clickCount(@ModelAttribute UserLoginRequest request, Model model) {
        model.addAttribute("username", request.getUsername());
        return "click-count";
    }
}
