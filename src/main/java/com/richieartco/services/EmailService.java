package com.richieartco.services;

import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(String to, String subject, String content, String name) throws MessagingException, UnsupportedEncodingException;


}
