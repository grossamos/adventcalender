package com.bundespolizei.adventcalender.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AdventQuestionSingletonTest {
    @Test
    void can_retrieve_questions_from_file() throws IOException {
        AdventQuestionSingleton adventQuestionSingleton = AdventQuestionSingleton.getInstance("./src/test/resources/test_question.json");
        assertEquals(adventQuestionSingleton.getQuestions()[0].getQuestion(), "Who is the coolest dude?");
        assertArrayEquals(adventQuestionSingleton.getQuestions()[0].getAnswers(), new String[]{"Amos","Ultraman","Amoooos"});
        assertEquals(adventQuestionSingleton.getQuestions()[0].getCorrectAnswerIndex(), 0);
    }
}