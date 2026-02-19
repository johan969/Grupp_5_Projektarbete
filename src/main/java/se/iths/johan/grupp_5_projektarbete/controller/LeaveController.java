package se.iths.johan.grupp_5_projektarbete.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.johan.grupp_5_projektarbete.model.Leave;
import se.iths.johan.grupp_5_projektarbete.service.LeaveService;

@Controller
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public String getAllLeaves(Model model) {
        model.addAttribute("leaves", leaveService.getAll());
        return "leaves"; // eller "leaves" beroende på din html-fil
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("leave", new Leave());
        return "create-leave";
    }

    @PostMapping
    public String createLeave(@ModelAttribute Leave leave) {
        leaveService.create(leave);
        return "redirect:/leaves";
    }


    @GetMapping("/{id}")
    public String getLeave(@PathVariable Long id, Model model) {
        model.addAttribute("leave", leaveService.getById(id));
        return "leave";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("leave", leaveService.getById(id));
        return "edit-leave";
    }

    @PostMapping("/{id}/update")
    public String updateLeave(@PathVariable Long id, @ModelAttribute Leave leave) {
        leaveService.update(id, leave);
        return "redirect:/leaves";
    }

    @PostMapping("/{id}")
    public String deleteLeave(@PathVariable Long id) {
        leaveService.delete(id);
        return "redirect:/leaves";
    }
}
