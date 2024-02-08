package com.zorrix.parser;

import java.util.ArrayList;

public class Week {
    private ArrayList<DayNSubjects> daysList;
    private int id;

    public Week(ArrayList<DayNSubjects> daysList, int id) {
        this.daysList = daysList;
        this.id = id;
    }

    public void addDay(DayNSubjects day){
        this.daysList.add(day);
    }

}
