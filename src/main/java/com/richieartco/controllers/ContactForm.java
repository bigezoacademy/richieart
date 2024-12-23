package com.richieartco.controllers;

import lombok.Data;

@Data
public class ContactForm {
    private String name;
    private String phone;
    private String email;
    private String message;
}
