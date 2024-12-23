package com.richieartco.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadedController {
    @GetMapping("/uploaded")
    public String uploadDocx() {
        return "uploaded";
    }
}
