package com.richieartco.controllers;

import com.richieartco.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String home(Model model) {
        Map<String, List<String>> bedroomsByCategory = projectService.getDistinctBedroomsByCategory();

        model.addAttribute("bedroomsByCategory", bedroomsByCategory);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
