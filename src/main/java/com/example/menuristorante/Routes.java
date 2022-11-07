package com.example.menuristorante;

import com.example.menuristorante.definitions.Beverage;
import com.example.menuristorante.definitions.Dish;
import com.example.menuristorante.definitions.Menu;
import com.example.menuristorante.definitions.constants.BeverageTypes;
import com.example.menuristorante.definitions.constants.FirstCourseType;
import com.example.menuristorante.definitions.constants.SecondCourseTypes;
import com.example.menuristorante.definitions.interfaces.APIs;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class Routes implements APIs {
    Menu menu = new Menu();

    public Routes() {
        Dish firstCourseDish = new Dish(
                "Pasta",
                "Pasta con pomodoro",
                9.0,
                200,
                FirstCourseType.MEAT);
        Dish firstCourseDish2 = new Dish(
                "Spaghetti alle vongole",
                "La ricetta degli spaghetti alle vongole è uno dei più grandi classici della cucina italiana. Si tratta di una tradizione campana amata ovunque!",
                15.0,
                500,
                FirstCourseType.FISH);
        Dish secondCourseDish = new Dish(
                "Salmone croccante",
                "Il salmone croccante è un secondo di mare molto semplice da realizzare ma di grande effetto grazie alla panatura al profumo di erbe aromatiche!",
                5.5,
                100,
                SecondCourseTypes.SEAFOOD);
        Dish secondCourseDish2 = new Dish(
                "Pollo al limone",
                "Il pollo al limone è un secondo piatto molto semplice da realizzare e di grande effetto grazie alla sua colorazione gialla e al suo profumo intenso!",
                10.0,
                300,
                SecondCourseTypes.LAND_FOOD);
        Beverage beverage = new Beverage(
                "Acqua",
                "Acqua Minerale Naturale",
                3.0,
                100,
                false,
                BeverageTypes.WATER);
        Beverage beverage2 = new Beverage(
                "Prosecco DOC",
                "Prosecco DOC",
                18.0,
                200,
                true,
                BeverageTypes.WINE);

        menu.addFirstCourse(firstCourseDish);
        menu.addFirstCourse(firstCourseDish2);
        menu.addSecondCourse(secondCourseDish);
        menu.addSecondCourse(secondCourseDish2);
        menu.addBeverage(beverage);
        menu.addBeverage(beverage2);
    }


    @Override
    public String getConsumable(String name) {
        Dish dish = menu.getDish(name);
        Beverage beverage = menu.getBeverage(name);

        return dish == null ? (beverage == null ? "{}" : beverage.toJSON()) : dish.toJSON();
    }
    @Override
    public String getMenu() {
        return menu.toJSON();
    }

    @Override
    public String chefFantasy(Double price, Integer calories) {
        Map<String, List<Dish>> chefFantasyDishes = menu.chefFantasy(price, calories);
        StringBuilder jsonResult = new StringBuilder("{");
        for (String k : chefFantasyDishes.keySet()) {
            jsonResult.append("\"").append(k).append("\"").append(": [");
            jsonResult.append(
                    chefFantasyDishes.get(k).stream().map(Dish::toJSON).reduce((s, s2) -> s + ", " + s2).orElse("")
            );
            jsonResult.append("],");
        }
        return jsonResult + "}";
    }

}