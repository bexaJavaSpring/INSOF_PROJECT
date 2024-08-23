package bekhruz.uz.controller;

import bekhruz.uz.domain.CodeHistory;
import bekhruz.uz.domain.User;
import bekhruz.uz.dtos.*;
import bekhruz.uz.repository.UserRepository;
import bekhruz.uz.service.GenerateCodeService;
import bekhruz.uz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final GenerateCodeService generateCodeService;

    @PostMapping(value = "/count/clicks")
    public String saveCountOfClicks(@ModelAttribute UserClickRequest request, Model model) {
        if (request.getX_count() != null) {
            CountResponseDto xresponse = generateCodeService.saveCountsOfClick(request);
            model.addAttribute("xresponse", xresponse);
        }
        if (request.getY_count() != null) {
            CountResponseDto yresponse = generateCodeService.saveCountsOfClick(request);
            model.addAttribute("yresponse", yresponse);
        }
        return "data-clicks";
    }

    @PostMapping(value = "/generate")
    public String generateCode(@ModelAttribute UserLoginRequest request, Model model) {
        String code = generateCodeService.generateCode();
        List<CodeHistoryDto> historyList = userService.saveCode(code, request.getUsername());
        model.addAttribute("code", code);
        model.addAttribute("history", historyList);
        model.addAttribute("message", "Successfully generated code!");
        return "login";
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute UserRegisterRequest request, Model model) {
        if (!isValidUserName(request.getUsername())) {
            model.addAttribute("message", "Username is not valid!");
            return "register";
        }
        User user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            model.addAttribute("message", "Username is already exist!");
            return "register";
        }
        String response = userService.register(request, model);
        model.addAttribute("message", response);
        return "register";
    }

    private boolean isValidUserName(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return true;
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute UserLoginRequest request, Model model) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            model.addAttribute("message", "This username has not been registered!");
            return "register";
        }
        userService.login(request.getCode(), user.getUsername());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("message", "You have successfully logged in!");
        return "click-count";
    }
}
