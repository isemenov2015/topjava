package ru.javawebinar.topjava.web;
import org.slf4j.Logger;
import ru.javawebinar.topjava.web.ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.web.ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static List<Meal> meals = new ArrayList<>();
    private static List<MealWithExceed> mealsWithExceed = new ArrayList<>();

    static {
        meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 29, 8, 0), "Завтрак", 300),
                new Meal(LocalDateTime.of(2015, Month.MAY, 29, 14, 0), "Обед", 400),
                new Meal(LocalDateTime.of(2015, Month.MAY, 29, 21, 0), "Ужин", 250),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 300),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1400),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 700),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1600),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 150),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        mealsWithExceed = MealsUtil.getFilteredWithExceeded(meals, LocalTime.of(6, 0), LocalTime.of(23, 0), 1800);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setAttribute("mealslist", mealsWithExceed);
        //response.sendRedirect("meals.jsp");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}