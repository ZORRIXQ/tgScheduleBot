package com.zorrix.parser;

import java.util.ArrayList;

public class DayNSubjects {
    private String dayOfWeek;
    private final String[] subjects;

    public DayNSubjects(String dayOfWeek, String[] subjects) {
        this.dayOfWeek = dayOfWeek;
        this.subjects = subjects;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String[] getSubjects() {
        return subjects;
    }


}
