package com.example.menuristorante.definitions.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface APIs {
    /**
     * From the menu, it returns the consumable with the given name
     * @param name the name of the consumable
     * @return the consumable
     */
    @GetMapping("/getConsumable")
    String getConsumable(@RequestParam String name);

    /**
     * Get the menu of the restaurant
     * @return the menu in JSON
     */
    @GetMapping("/getMenu")
    String getMenu();

    /**
     * Convert chefFantasy to JSON string filtered by <= price and <= calories
     * If a param is null it will be ignored applying only one filter
     * If both params are null it will return all the dishes
     *
     * @param price    max price of dishes
     * @param calories max calories of dishes
     * @return JSON string
     */
    @GetMapping("/chefFantasy")
    String chefFantasy(@RequestParam(required = false) Double price, @RequestParam(required = false) Integer calories);
}
