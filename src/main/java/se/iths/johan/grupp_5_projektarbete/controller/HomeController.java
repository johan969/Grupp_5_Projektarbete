package se.iths.johan.grupp_5_projektarbete.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }
}
