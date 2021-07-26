package com.bundespolizei.adventcalender.controller;

import com.bundespolizei.adventcalender.database.AdventRepository;
import com.bundespolizei.adventcalender.helper.Randomiser;
import com.bundespolizei.adventcalender.helper.ResourceUtil;
import com.bundespolizei.adventcalender.model.AdventQuestionSingleton;
import com.bundespolizei.adventcalender.model.ParticipantDbEntry;
import com.bundespolizei.adventcalender.model.QuestionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdventRepository adventRepository;

    @GetMapping("/{authToken}")
    public String landingPage(Model model, @PathVariable String authToken) {
        if (!hasProperAuth(authToken)) {
            return "error";
        }
        model.addAttribute("authToken", authToken);

        List<ParticipantDbEntry> participants = StreamSupport.stream(adventRepository.findAll().spliterator(), false).collect(Collectors.toList());
        model.addAttribute("participants", participants);
        model.addAttribute("random", participants.get(Randomiser.getRandomNumber(0, participants.size())));
        return "admin/index";
    }

    @GetMapping("/{authToken}/winner/of/{day}")
    public String potentialWinners(Model model, @PathVariable int day, @PathVariable String authToken) throws IOException {
        logger.info(authToken);
        if (!hasProperAuth(authToken)) {
            return "error";
        }
        model.addAttribute("authToken", authToken);

        AdventQuestionSingleton questionSingleton = AdventQuestionSingleton.getInstance(ResourceUtil.QUESTION_PATH);
        QuestionModel question = questionSingleton.getQuestions()[day - 1];
        LocalDate today = LocalDate.now();
        List participants = adventRepository.findAllByEntryDateAndAndSelectedIndex(LocalDate.of(today.getYear(), today.getMonth(), day), question.getCorrectAnswerIndex());
        model.addAttribute("participants", participants);
        model.addAttribute("random", participants.get(Randomiser.getRandomNumber(0, participants.size())));
        return "admin/index";
    }

    @GetMapping("/{authToken}/winner")
    public RedirectView winnerRedirect(@PathVariable String authToken) {
        return new RedirectView("/admin/" + authToken + "/winner/of/" + LocalDate.now().getDayOfMonth());
    }

    // TODO implement propper Auth with Spring security
    private boolean hasProperAuth(String authToken) {
        logger.info(System.getenv("JWT_SECRET"));
        return authToken.equals(System.getenv("ADMIN_TOKEN"));
    }

}
