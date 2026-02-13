package se.iths.johan.grupp_5_projektarbete.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.johan.grupp_5_projektarbete.service.LeaveService;

@Controller
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("leaves", leaveService.getAll());
        return "leave-list";
    }
}
