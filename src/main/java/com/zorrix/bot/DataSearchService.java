package com.zorrix.bot;

import com.zorrix.parser.DayNSubjects;

import java.util.*;

public class DataSearchService {
    private HashMap<Integer, ArrayList<DayNSubjects>> dataMap;

    public DataSearchService(HashMap<Integer, ArrayList<DayNSubjects>> map){
        this.dataMap = map;
    }

    public String findDaySubjects(boolean TomorrowSchedule){
        Calendar calendar = Calendar.getInstance();

        if (TomorrowSchedule)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        //number(index) of week we're going to search in
        int weekToSearchIn = calendar.get(Calendar.WEEK_OF_MONTH);

        StringBuilder stringBuilder = new StringBuilder("Subjects on the " + calendar.get(Calendar.DAY_OF_MONTH)
                + " " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + ":\n");

        StringBuilder temp = new StringBuilder();
        //iterating trough each dataMap's week
        for (DayNSubjects dayNSubjects : this.dataMap.get(weekToSearchIn)){

            //printing searched day of week and it's subjects
            if (dayNSubjects.getDayOfWeek().toLowerCase().equals(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH).toLowerCase()) ||
                    dayNSubjects.getDayOfWeek().equals(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()).toLowerCase()) ) {

                temp.append(dayNSubjects.getDayOfWeek() + ": ");

                for (int i = 0; i < dayNSubjects.getSubjects().length; i++)
                    temp.append("\n" + (i+1) + ". " + dayNSubjects.getSubjects()[i].trim());

//                        + Arrays.toString(dayNSubjects.getSubjects()).replaceAll("]", "\n").replaceAll("\\[", "") + "\n");
            }
        }

        if (temp.isEmpty())
            temp.append("No subjects " + (TomorrowSchedule ? "tomorrow" : "today"));

        stringBuilder.append(temp);
        return stringBuilder.toString();
    }
}
