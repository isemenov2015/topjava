package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(AuthorizedUser.id());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {

        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll() {

        List<Meal> meals = repository.values().stream()
                                                    .filter(meal -> meal.getUserId() == AuthorizedUser.id())
                                                    .sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime)))
                                                    .collect(Collectors.toList());
        if (meals == null || meals.size() == 0)
            meals = null;
        return meals;
    }
}

