package com.bundespolizei.adventcalender.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;

public class AdventQuestionSingleton {
    private final QuestionModel[] questions;
    private static AdventQuestionSingleton instance;

    private AdventQuestionSingleton(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.questions = mapper.readValue(Paths.get(path).toFile(), QuestionModel[].class);
    }

    public static AdventQuestionSingleton getInstance(String path) throws IOException {
        if (instance == null) {
            instance = new AdventQuestionSingleton(path);
        }
        return instance;
    }

    public QuestionModel getTodaysQuestion() {
        LocalDate today = LocalDate.now();
        return questions[today.getDayOfMonth() - 1];
    }

    public QuestionModel[] getQuestions() {
        return questions;
    }
}
