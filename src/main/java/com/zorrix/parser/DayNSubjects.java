package com.zorrix.parser;

//class that contains String day of week and an arraylist with subjects for that day
public class DayNSubjects {
    private String dayOfWeek;
    private final String[] subjects;

    public DayNSubjects(String dayOfWeek, String[] subjects) {
        this.dayOfWeek = dayOfWeek;
        this.subjects = subjects;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String[] getSubjects() {
        return subjects;
    }


}
