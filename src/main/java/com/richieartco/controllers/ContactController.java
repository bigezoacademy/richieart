package com.richieartco.controllers;

import com.richieartco.serviceImplementation.SendSms;
import com.richieartco.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @PostMapping("/contact")
    public String sendContactEmail(@ModelAttribute("contactForm") ContactForm form, Model model) {
        try {
            // Build email content from the form data with HTML formatting
            String subject = "CUSTOMER INQUIRY";
            String senderName = form.getName();
            String mailContent = "<p><strong>Sender:</strong> " + senderName + "</p>" +
                    "<p><strong>Phone:</strong> " + form.getPhone() + "</p>" +
                    "<p><strong>Email:</strong> " + form.getEmail() + "</p>" +
                    "<p><strong>Message:</strong> " + form.getMessage() + "</p>";

            // Send email
            emailService.sendEmail("admin@richieart.co", subject, mailContent, senderName);
            SendSms send=new SendSms();
          //  send.sendSms("RICHIEART INQUIRY_from-"+form.getName()+"--"+form.getMessage()   ,"+256773913902");
          //  send.sendSms("RICHIEART INQUIRY_from-"+form.getName()+"--"+form.getMessage()   ,"+256776407600");


            // Clear the form and show success message
            model.addAttribute("success", true);
            model.addAttribute("contactForm", new ContactForm());
        } catch (Exception e) {
            model.addAttribute("error", true);
        }

        return "contact";
    }


}
