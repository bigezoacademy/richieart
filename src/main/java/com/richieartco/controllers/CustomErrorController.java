package com.richieartco.controllers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null && Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()) {
            return "404";  // You may need to adjust this based on your 404 handling
        }

        // Get the error message from the request attribute
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        model.addAttribute("errorMessage", errorMessage);

        return "error";
    }
}

