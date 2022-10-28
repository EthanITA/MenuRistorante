package com.example.menuristorante.definitions;

import com.example.menuristorante.definitions.interfaces.Consumable;

public class Beverage implements Consumable {
    // I ml e alcoholic sono final perchè in genere non hanno senso essere modificati
    public final Integer ml;
    public final Boolean isAlcoholic;
    public final String type;
    public Double price;
    private String name;
    private String description;


    public Beverage(String name, String description, Double price, Integer ml, Boolean isAlcoholic, String type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.ml = ml;
        this.isAlcoholic = isAlcoholic;
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

    public String toJSON() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"price\":" + price +
                ", \"description\":\"" + description + '\"' +
                ", \"ml\":" + ml +
                ", \"isAlcoholic\":" + isAlcoholic +
                ", \"type\":\"" + type + '\"' +
                '}';
    }
}

