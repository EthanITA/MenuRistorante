package com.example.menuristorante.definitions;

import com.example.menuristorante.definitions.interfaces.Consumable;

public class Dish implements Consumable {
    public final String type;
    protected String name;
    protected Double price;
    protected String description;
    protected Integer calories;

    public Dish(String name, String description, Double price, Integer calories, String type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public boolean equals(Dish dish) {
        if (dish == null) return false;
        return this.name.equals(dish.name) &&
                this.price.equals(dish.price) &&
                this.description.equals(dish.description) &&
                this.calories.equals(dish.calories) &&
                this.type.equals(dish.type);
    }

    public String toJSON() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"price\":" + price +
                ", \"description\":\"" + description + '\"' +
                ", \"calories\":" + calories +
                ", \"type\":\"" + type + '\"' +
                '}';
    }

}
