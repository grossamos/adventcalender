package com.bundespolizei.adventcalender.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String landingPage(Model model) {
        logger.info("Hello World");
        model.addAttribute("message", "Issa me Amosio");

        return "index";
    }
}