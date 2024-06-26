package com.tretyakov.api;

import com.tretyakov.model.Book;
import com.tretyakov.model.Reader;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Issue {

    private UUID id;
    private LocalDate issuedAt;
    private Book book;
    private Reader reader;

}
