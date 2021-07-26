package com.bundespolizei.adventcalender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String landingPage(Model model) {
        return "admin/index";
    }
}
