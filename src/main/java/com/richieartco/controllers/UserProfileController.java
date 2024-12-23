package com.richieartco.controllers;

import com.richieartco.security.EndToEndUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {
    @GetMapping("/profile")
    public String userprofile(Model model) {
        model.addAttribute("subtitleText", "MY PROFILE");

        // Get the authenticated user's details, including firstName
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EndToEndUserDetails) {
            EndToEndUserDetails userDetails = (EndToEndUserDetails) authentication.getPrincipal();
            model.addAttribute("firstName", userDetails.getFirstName());
            model.addAttribute("lastName", userDetails.getLastName());
            model.addAttribute("email", userDetails.getUsername());
            model.addAttribute("phone", userDetails.getPhone());
        }

        return "profile";
    }
}
