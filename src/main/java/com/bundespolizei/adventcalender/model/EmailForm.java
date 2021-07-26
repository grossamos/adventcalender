package com.bundespolizei.adventcalender.model;

import java.io.Serializable;

public class EmailForm implements Serializable {
    private String emailadress;

    public EmailForm() {
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }
}
