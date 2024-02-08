package com.zorrix.bot;

import com.zorrix.parser.DayNSubjects;

import java.util.*;

public class DataSearch {
    private HashMap<Integer, ArrayList<DayNSubjects>> dataMap;

    public DataSearch(HashMap<Integer, ArrayList<DayNSubjects>> map){
        this.dataMap = map;
    }

    //should this method return String result, or DayNSubjects object for current day?
    public String findDaySubjects(){
        Calendar calendar = Calendar.getInstance();
        int weekToSearchIn = calendar.get(Calendar.WEEK_OF_MONTH);

        StringBuilder stringBuilder = new StringBuilder("Subjects on the " + calendar.get(Calendar.DAY_OF_MONTH)
                + " " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + ":\n");

        //iterating trough each dataMap's week
        for (DayNSubjects dayNSubjects : this.dataMap.get(weekToSearchIn)){

            //printing searched day of week and it's subjects

            if (dayNSubjects.getDayOfWeek().equals(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)) ||
                    dayNSubjects.getDayOfWeek().equals(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())) ) {
                stringBuilder.append(dayNSubjects.getDayOfWeek() + ": "
                        + Arrays.toString(dayNSubjects.getSubjects()).replaceAll("[\\Q[]\\E]", "") + "\n");
            }
        }

        return stringBuilder.toString();
    }
}
