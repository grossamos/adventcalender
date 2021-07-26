package com.bundespolizei.adventcalender.controller;

import com.bundespolizei.adventcalender.database.AdventRepository;
import com.bundespolizei.adventcalender.helper.Randomiser;
import com.bundespolizei.adventcalender.helper.ResourceUtil;
import com.bundespolizei.adventcalender.model.AdventQuestionSingleton;
import com.bundespolizei.adventcalender.model.ParticipantDbEntry;
import com.bundespolizei.adventcalender.model.QuestionModel;
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

    @Autowired
    private AdventRepository adventRepository;

    @GetMapping("/")
    public String landingPage(Model model) {
        List<ParticipantDbEntry> participants = StreamSupport.stream(adventRepository.findAll().spliterator(), false).collect(Collectors.toList());
        model.addAttribute("participants", participants);
        model.addAttribute("random", participants.get(Randomiser.getRandomNumber(0, participants.size())));
        return "admin/index";
    }

    @GetMapping("/winner/of/{day}")
    public String potentialWinners(Model model, @PathVariable int day) throws IOException {
        AdventQuestionSingleton questionSingleton = AdventQuestionSingleton.getInstance(ResourceUtil.QUESTION_PATH);
        QuestionModel question = questionSingleton.getQuestions()[day - 1];
        LocalDate today = LocalDate.now();
        List participants = adventRepository.findAllByEntryDateAndAndSelectedIndex(LocalDate.of(today.getYear(), today.getMonth(), day), question.getCorrectAnswerIndex());
        model.addAttribute("participants", participants);
        model.addAttribute("random", participants.get(Randomiser.getRandomNumber(0, participants.size())));
        return "admin/index";
    }

    @GetMapping("/winner")
    public RedirectView winnerRedirect() {
        return new RedirectView("/admin/winner/of/" + LocalDate.now().getDayOfMonth());
    }

}
