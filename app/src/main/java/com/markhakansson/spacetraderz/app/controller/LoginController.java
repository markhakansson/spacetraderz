package com.markhakansson.spacetraderz.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }
}
