package com.bundespolizei.adventcalender.model;

import java.io.Serializable;

public class EmailForm implements Serializable {
    private String emailadress;
    private int selectedAnswerIndex;

    public EmailForm() {
    }

    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }

    public void setSelectedAnswerIndex(int selectedAnswerIndex) {
        this.selectedAnswerIndex = selectedAnswerIndex;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }
}
