import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/* NOTE : It is also assumed that Restaurant Service includes taking orders from the user and also updating them with latest information.
Hence a new class is implemented with user mechanism and it is included here in the Restaurant class.*/

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static user user;
    public Restaurant restaurant_chosen;

    public Restaurant findRestaurantByName(String restaurantName){
        for (Restaurant res : getRestaurants())
        {
            //System.out.println(res.getName());
            if (res.getName() == restaurantName) {
                return res;
            }
        }
        return null;
        //Finished - DELETE ABOVE STATEMENT AND WRITE CODE HERE
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

    public void setRestaurantChosen(Restaurant restaurant_chosen) {
        this.restaurant_chosen=restaurant_chosen;
    }
    public void setUser (user user) {
        this.user = user;
    }
}
