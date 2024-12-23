package com.richieartco.user;

import com.richieartco.security.EndToEndUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Optional;

/**
 * @author Alfred Ochola
 */
@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;



    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("subtitleText", "USER ACCOUNTS");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EndToEndUserDetails) {
            EndToEndUserDetails userDetails = (EndToEndUserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            // Check if the email is equal to "admin@richieart.co"
            if ("admin@richieart.co".equals(email)) {
                model.addAttribute("users", userService.getAllUsers());
                // Redirect to the admin page
                return "users";
            }
            else{
                return "redirect:/error";
                        }
        }
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("subtitleText", "EDIT USER ACCOUNT");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EndToEndUserDetails) {
            EndToEndUserDetails userDetails = (EndToEndUserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            // Check if the email is equal to "admin@richieart.co"
            if ("admin@richieart.co".equals(email)) {
                Optional<User> user = userService.findById(id);
                model.addAttribute("user", user.get());
                return "update-user";
            }
            else{
                return "redirect:/error";
            }
        }
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, Model model){

        model.addAttribute("subtitleText", "EDIT USER ACCOUNT");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EndToEndUserDetails) {
            EndToEndUserDetails userDetails = (EndToEndUserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            // Check if the email is equal to "admin@richieart.co"
            if ("admin@richieart.co".equals(email)) {
                userService.updateUser(id, user.getFirstName(), user.getLastName(),user.getPhone(), user.getEmail());
                return "redirect:/users?update_success";
            }
            else{
                return "redirect:/error";
            }
        }
        return  "redirect:/users?update_success";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){

        model.addAttribute("subtitleText", "DELETE USER ACCOUNT");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof EndToEndUserDetails) {
            EndToEndUserDetails userDetails = (EndToEndUserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            // Check if the email is equal to "admin@richieart.co"
            if ("admin@richieart.co".equals(email)) {
                userService.deleteUser(id);
                   return "redirect:/users?delete_success";
            }
            else{
                return "redirect:/error";
            }
        }
        return "redirect:/users?delete_success";
    }
}
