import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private Restaurant restaurant ;


    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
//        return null;
        for (Restaurant restaurantByName : restaurants) {

            if (restaurantByName.getName().equals(restaurantName)) {
                restaurant = restaurantByName;
            }

        }

        if (null == restaurant) throw new restaurantNotFoundException("Restaurant not found");
       else{
           return restaurant;
       }
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
