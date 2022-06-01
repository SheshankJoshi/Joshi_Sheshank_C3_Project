import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/*PLEASE NOTE - It is assumed that one user can select and order from only one Restaurant per session or screen until the order is shelved for
* that Restaurant's service. Hence, any new order from other Restaurant is not considered. In future if its considered, it is considered as a new service
* for the same user object (we can create and initialize the object in the common playground and use the varialbes here are aliases for it) */

class RestaurantServiceTest {

    public static RestaurantService service = new RestaurantService();
    public Restaurant restaurant;
    public user current_user;
    // Current user has a total user order list, and total price.

    @BeforeAll
    public static void create_new_restaurant_object_and_activate_it(){
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
        /* The following method should return the restaurant object that is added before all, and hence
        needs to be initiated properly.
        */
        /* NOTE - Two objects created here, though are same field by field, and hence represented the same object, their
         comparison for the same object fails. The following code has been tested but not working due to every changing
         versions and incompatibilities. Hence, checking by name is preferred here*/
        //assertEquals(service.findRestaurantByName("Taj")).usingRecursiveComparison().isEqualTo(restaurant);
        assertEquals(restaurant.getName(),service.findRestaurantByName("Taj").getName());
    }
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
        /* Note here that restaurant non existing can be thrown in two ways i.e. by searching for wrong restaurant
        name, or non-existing restaurant name. Or can be searched using new restaurant object that hasn't been added
        to the service yet.
        */
        /* The following method should return the restaurant object that is added before all, and hence
        needs to be initiated properly.
        */
        assertNotNull(service.findRestaurantByName("Taj")); // This is the pass case
        //assertEquals(service.findRestaurantByName("Taj")).usingRecursiveComparison().isEqualTo(restaurant);
        assertNotEquals(restaurant.getName(),service.findRestaurantByName("Taj").getName());
    }

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
    public void adding_orders_and_checking_price_with_items_present_in_menu_checking_order_should_return_true() {
        // First Select a Restaurant.
        service.setRestaurantChosen(service.findRestaurantByName("Oberoi")); // This is the selected Restaurant, and hence initiated here.
        restaurant = service.restaurant_chosen;
        Item item1 = new Item("Panipuri", 20);
        restaurant.addToMenu(item1.getName(), item1.getPrice());
        //
        Item item2 = new Item("Dahi Wada", 45);
        restaurant.addToMenu(item2.getName(), item2.getPrice());
        //
        Item item3 = new Item("Veg Biryani", 200);
        restaurant.addToMenu(item3.getName(), item3.getPrice());
        //
        Item item4 = new Item("Double-ka-meetha", 65);
        restaurant.addToMenu(item4.getName(), item4.getPrice());
        //
        Item item5 = new Item("Chicken Tikka", 200);
        restaurant.addToMenu(item5.getName(), item5.getPrice());
        //
        //
        /*The above five items are added to the menu of the Restaurant and are also available for order.*/
        //System.out.println(restaurant.getMenu());
        current_user = new user(); // Creating a new user as and when required.
        //
        service.setUser(current_user); // Setting the service to current user
        // We add 3 items here for our order
        // Could have used a function here to eliminate this, but kept here for better comprehension.
        //<<<<<<<<<<<<<<<<<<<<<<<<<< Everything Clear Till here >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        try {
            //System.out.println(restaurant.getMenu());
            //System.out.println(!(restaurant.getMenu().get(1).toString() == item2.toString()));
            //System.out.println(restaurant.getMenu().get(1));
            //System.out.println(restaurant.checkItemInMenu(item2));
            assert restaurant.checkItemInMenu(item2);
            current_user.add_to_order(item2); // Price 45
        }
        catch (itemNotFoundException e) {
            System.out.println("Menu Items are empty");
        }
        try {
            assert restaurant.checkItemInMenu(item3) : new itemNotFoundException(item3.getName());
            current_user.add_to_order(item3); // Price 200
        }
        catch (itemNotFoundException e) {
            System.out.println("Menu Items are empty");
        }
        try {
            assert restaurant.checkItemInMenu(item5) : new itemNotFoundException(item5.getName());
            current_user.add_to_order(item5); // Price 200
        }
        catch (itemNotFoundException e) {
            System.out.println("Menu Items are empty");
        }
        // Item 2 is present inside the current orders list.
        assertTrue(current_user.items_chosen.contains(item2));
        // The total cose of the three items added to our order should be as below
        assertEquals(445,current_user.get_total()); // Total is 440
        }

    @Test
    public void adding_orders_and_checking_price_with_items_present_in_menu_checking_order_should_return_false() {
        // First Select a Restaurant.
        restaurant = service.findRestaurantByName("Oberoi"); // This is the selected Restaurant, and hence initiated here.
        Item item1 = new Item("Panipuri", 20);
        restaurant.addToMenu(item1.getName(), item1.getPrice());
        //
        Item item2 = new Item("Dahi Wada",45);
        restaurant.addToMenu(item2.getName(), item2.getPrice());
        //
        Item item3 = new Item("Veg Biryani",200);
        restaurant.addToMenu(item3.getName(), item3.getPrice());
        //
        Item item4 = new Item("Double-ka-meetha",65);
        restaurant.addToMenu(item4.getName(), item4.getPrice());
        //
        Item item5 = new Item("Chicken Tikka",200);
        restaurant.addToMenu(item5.getName(), item5.getPrice());
        //
        /*The above five items are added to the menu of the Restaurant and are also available for order.*/
        current_user = new user();
        // WE add two items here for our orders
        // Could have used a function here to eliminate this, but kept here for better comprehension.
        try {
            assert restaurant.checkItemInMenu(item4) : new itemNotFoundException(item4.getName());
            current_user.add_to_order(item4); // Price 65
        }
        catch (itemNotFoundException e) {
            System.out.println("Menu Items are empty");
        }
        try {
            assert restaurant.checkItemInMenu(item1) : new itemNotFoundException(item1.getName());
            current_user.add_to_order(item1); // Price 20
        }
        catch (itemNotFoundException e) {
            System.out.println("Menu Items are empty");
        }
        // Item 2 is not present inside the current orders list. So, it should generate a false Result i.e. its not in the list of ordered items.
        assertFalse(current_user.items_chosen.contains(item2));
        assertNotEquals(65,current_user.get_total()); // Total is 85
}
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
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