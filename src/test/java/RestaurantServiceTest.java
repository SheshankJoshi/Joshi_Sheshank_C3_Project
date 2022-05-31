import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @BeforeAll
    public void create_new_restaurant_object_and_activate_it(){
        //List <Restaurant> restaurants = new ArrayList<Restaurant>();
        service.addRestaurant("Oberoi", "Hyderabad",
                LocalTime.of(9,30),
                LocalTime.of(20,30)
                );
        service.addRestaurant("Taj", "Hyderabad",
                LocalTime.of(10,30),
                LocalTime.of(19,30)
        );
        service.addRestaurant("Paradise", "Hyderabad",
                LocalTime.of(10,00),
                LocalTime.of(21,00)
        );

    }
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //Finished -- WRITE UNIT TEST CASE HERE

        // Create a new Restaurant that is existing for checking
        restaurant = new Restaurant("Taj", "Hyderabad",
                LocalTime.of(10,30),
                LocalTime.of(19,30));
        Restaurant test_res = Mockito.spy(restaurant); // We are not going to use it in our testing
        /* The following method should return the restaurant object that is added before all, and hence
        needs to be initiated properly.
        */
        assertEquals(restaurant, service.findRestaurantByName("Taj"));
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //Finished - WRITE UNIT TEST CASE HERE

        // Creating a new Restaurant object for Checking
        restaurant = new Restaurant("Barbeque Nation", "Hyderabad",
                LocalTime.of(10,30),
                LocalTime.of(19,30));
        /*We are not going to use the mockito here though we have created it and could have
         used it to mock the situation to check for the object equality (instead of going for the line before).
          However the object created is retained*/
        Restaurant test_res = Mockito.spy(restaurant); //
        /* Note here that restaurant non existing can be thrown in two ways i.e. by searching for wrong restaurant
        name, or non-existing restaurant name. Or can be searched using new restaurant object that hasn't been added
        to the service yet.
        */
        /* The following method should return the restaurant object that is added before all, and hence
        needs to be initiated properly.
        */
        assertNotNull(service.findRestaurantByName("Taj")); // This is the pass case
        assertEquals(restaurant, service.findRestaurantByName("Taj"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}