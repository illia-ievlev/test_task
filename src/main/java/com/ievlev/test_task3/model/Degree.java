package com.ievlev.test_task3.model;


import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Optional;

@RequiredArgsConstructor
public enum Degree {
    ASSISTANT("assistant"), ASSOCIATE_PROFESSOR("associate professor"), PROFESSOR("professor");

    private final String name;

    public String getName() {
        return name;
    }

    public static Optional<Degree> fromName(String name) {
        return EnumSet.allOf(Degree.class).stream().filter(t -> t.getName().equals(name)).findFirst();
    }
}
