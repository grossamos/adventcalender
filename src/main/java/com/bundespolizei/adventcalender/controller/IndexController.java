package com.bundespolizei.adventcalender.controller;

import com.bundespolizei.adventcalender.database.AdventRepository;
import com.bundespolizei.adventcalender.helper.JwtUtil;
import com.bundespolizei.adventcalender.helper.ResourceUtil;
import com.bundespolizei.adventcalender.model.AdventQuestionSingleton;
import com.bundespolizei.adventcalender.model.EmailForm;
import com.bundespolizei.adventcalender.model.ParticipantDbEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AdventRepository adventRepository;

    @GetMapping("/")
    public String landingPage(Model model) throws IOException {
        AdventQuestionSingleton questions = AdventQuestionSingleton.getInstance(ResourceUtil.QUESTION_PATH);
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
    public String answerForm(@PathVariable int answerIndex, Model model, @CookieValue(value = "email", defaultValue = "NONE") String emailJWT) {
        String email = JwtUtil.decodeJWT(emailJWT, JwtUtil.KEY);
        if (!emailJWT.equals("NONE") && !email.equals("")) {
            adventRepository.save(new ParticipantDbEntry(email, LocalDate.now(), answerIndex));
            model.addAttribute("isOkay", true);
            return "submit";
        }

        model.addAttribute("answer", answerIndex);
        model.addAttribute("someGivenAnswer", answerIndex);
        model.addAttribute("emailform", new EmailForm());
        return "email_form";
    }

    @PostMapping("/submit/{answerIndex}")
    public String emailSubmission(@PathVariable int answerIndex, @ModelAttribute EmailForm emailForm, Model model, HttpServletResponse response) {
        boolean isOkay = emailForm.getEmailadress().endsWith("@amosgross.com");
        model.addAttribute("isOkay", isOkay);
        if (isOkay && !adventRepository.existsById(emailForm.getEmailadress())) {
            adventRepository.save(new ParticipantDbEntry(emailForm.getEmailadress(), LocalDate.now(), answerIndex));
            Cookie cookie = new Cookie("email", JwtUtil.encodeAsJWT(emailForm.getEmailadress(), JwtUtil.KEY));
            cookie.setMaxAge(25 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "submit";
    }
}