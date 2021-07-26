package com.bundespolizei.adventcalender.controller;

import com.bundespolizei.adventcalender.model.AdventQuestionSingleton;
import com.bundespolizei.adventcalender.model.EmailForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String landingPage(Model model) throws IOException {
        AdventQuestionSingleton questions = AdventQuestionSingleton.getInstance("./src/test/resources/test_question.json");
        LocalDate today = LocalDate.now();
        model.addAttribute("question", questions.getTodaysQuestion());
        return "index";
    }

    @GetMapping("/admin")
    public RedirectView adminRedirect() {
        // redirect to the actual location of the admin panel
        return new RedirectView("/admin/");
    }

    @GetMapping("/answer/{answerIndex}")
    public String answerForm(@PathVariable int answerIndex, Model model) {
        model.addAttribute("answer", answerIndex);
        model.addAttribute("someGivenAnswer", answerIndex);
        model.addAttribute("emailform", new EmailForm());
        return "email_form";
    }

    @PostMapping("/submit/{answerIndex}")
    @GetMapping("/submit/{answerIndex}")
    public String emailSubmission(@PathVariable int answerIndex, @ModelAttribute EmailForm emailForm, Model model) {
        boolean isOkay = emailForm.getEmailadress().endsWith("@amosgross.com");
        model.addAttribute("isOkay", isOkay);
        if (isOkay) {
            // TODO add interaction with database
            logger.info(emailForm.getEmailadress() + " " + emailForm.getSelectedAnswerIndex());
        }
        return "submit";
    }
}