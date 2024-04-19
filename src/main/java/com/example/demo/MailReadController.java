package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailReadController {

    @Autowired
    private MailService mailService;

    @GetMapping("/read-emails")
    public String readEmails() {
        mailService.readEmails();
        return "Reading emails...";
    }
}
