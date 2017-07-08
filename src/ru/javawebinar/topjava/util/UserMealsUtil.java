package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // returns filtered list with correctly exceeded field
        Map<LocalDate, Integer> caloriesCounter = new HashMap<>();
        List<UserMealWithExceed> filteredMealList = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalDate date = meal.getDateTime().toLocalDate();
            int totalCalories = meal.getCalories();
            if (caloriesCounter.containsKey(date)) {
                totalCalories += caloriesCounter.get(date);
            }
            caloriesCounter.put(date, totalCalories);
        }
        for (UserMeal meal : mealList) {
            // Check if a record satisfies filtering condition
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                LocalDate date = meal.getDateTime().toLocalDate();
                int calories = caloriesCounter.get(date);
                UserMealWithExceed umwe = new UserMealWithExceed(meal.getDateTime(), meal.getDescription(),
                                                                    calories, calories > caloriesPerDay);
                filteredMealList.add(umwe);
            }
        }
        return filteredMealList;
    }
}
