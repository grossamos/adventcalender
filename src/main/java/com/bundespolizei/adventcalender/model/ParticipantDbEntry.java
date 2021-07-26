package com.bundespolizei.adventcalender.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "participants")
public class ParticipantDbEntry {
    @Id
    private String emailAddress;
    private LocalDate entryDate;
    private Integer selectedIndex;

    public ParticipantDbEntry() {
    }

    public ParticipantDbEntry(String emailAddress, LocalDate entryDate, Integer selectedIndex) {
        this.emailAddress = emailAddress;
        this.entryDate = entryDate;
        this.selectedIndex = selectedIndex;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(Integer selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
}
