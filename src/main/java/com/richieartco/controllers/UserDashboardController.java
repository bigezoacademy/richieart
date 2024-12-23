package com.richieartco.controllers;

import com.richieartco.security.EndToEndUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {
    @GetMapping("/user")
    public String userdashboard(Model model, HttpSession session) {
        model.addAttribute("subtitleText", "USER DASHBOARD");

        // Get the authenticated user's details, including email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EndToEndUserDetails) {
            EndToEndUserDetails userDetails = (EndToEndUserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            // Check if the email is equal to "admin@richieart.co"
            if ("admin@richieart.co".equals(email)) {
                // Redirect to the admin page
                return "redirect:/admin";
            }

            model.addAttribute("firstName", userDetails.getFirstName());
            model.addAttribute("lastName", userDetails.getLastName());
            model.addAttribute("email", email);
            model.addAttribute("phone", userDetails.getPhone());
            String phone = userDetails.getPhone();
            String last5Digits = phone.substring(Math.max(phone.length() - 5, 0));

            // Set the session attribute
            session.setAttribute("last5Digits", last5Digits);
        }

        return "user";
    }
}
