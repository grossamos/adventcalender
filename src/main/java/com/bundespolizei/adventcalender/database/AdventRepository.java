package com.bundespolizei.adventcalender.database;

import com.bundespolizei.adventcalender.model.ParticipantDbEntry;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdventRepository extends CrudRepository<ParticipantDbEntry, String> {
    List<ParticipantDbEntry> findAllByEntryDateAndAndSelectedIndex(LocalDate entryDate, Integer selectedIndex);
}
