package com.tretyakov.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Book {

    private UUID id;
    private String name;
    private Author author;

}
