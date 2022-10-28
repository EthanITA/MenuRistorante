package com.example.menuristorante.definitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Menu {
    private final List<Dish> firstCourses;
    private final List<Dish> secondCourses;
    private final List<Beverage> beverages;

    public Menu(List<Dish> firstCourses, List<Dish> secondCourses, List<Beverage> beverages) {
        this.firstCourses = firstCourses;
        this.secondCourses = secondCourses;
        this.beverages = beverages;
    }

    public Menu() {
        this.firstCourses = new ArrayList<>();
        this.secondCourses = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    public void addFirstCourse(Dish dish) {
        firstCourses.add(dish);
    }

    public void addSecondCourse(Dish dish) {
        secondCourses.add(dish);
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public Dish getDish(String name) {
        Dish firstCourseDish = firstCourses.stream().filter(dish -> dish.getName().equals(name)).findFirst().orElse(null);
        Dish secondCourseDish = secondCourses.stream().filter(dish -> dish.getName().equals(name)).findFirst().orElse(null);
        return firstCourseDish != null ? firstCourseDish : secondCourseDish;
    }

    public Beverage getBeverage(String name) {
        return beverages.stream().filter(beverage -> beverage.getName().equals(name)).findFirst().orElse(null);
    }

    public String toJSON() {
        return "{\n" +
                "  \"First Courses\": " + "[" + this.firstCourses.stream().map(Dish::toJSON).reduce((s, s2) -> s + ", " + s2).orElse("") + "],\n" +
                "  \"Second Courses\": " + "[" + this.secondCourses.stream().map(Dish::toJSON).reduce((s, s2) -> s + ", " + s2).orElse("") + "],\n" +
                "  \"Beverages\": " + "[" + this.beverages.stream().map(Beverage::toJSON).reduce((s, s2) -> s + ", " + s2).orElse("") + "]\n" +
                "}";
    }

    public Map<String, List<Dish>> chefFantasy(Double price, Integer calories) {
        Predicate<Dish> predicate;
        if (price != null && calories != null)
            predicate = dish -> dish.getPrice() <= price && dish.getCalories() <= calories;
        else if (price != null)
            predicate = dish -> dish.getPrice() <= price;
        else if (calories != null)
            predicate = dish -> dish.getCalories() <= calories;
        else
            predicate = dish -> true;
        return groupFirstCoursesByType(predicate);
    }

    /**
     * @param predicate<Dish> a predicate to filter the dishes
     * @return a map of filtered dishes grouped by their type
     * eg. Meat -> [Dish1, Dish2], Fish -> [Dish3, Dish4]
     */
    private Map<String, List<Dish>> groupFirstCoursesByType(Predicate<Dish> predicate) {
        Map<String, List<Dish>> dishesGroupedByType = this.firstCourses.stream().collect(Collectors.groupingBy(dish -> dish.type));
        dishesGroupedByType.keySet().forEach(k -> dishesGroupedByType.put(k, dishesGroupedByType.get(k).stream().filter(predicate).collect(Collectors.toList())));
        return dishesGroupedByType;
    }



}
