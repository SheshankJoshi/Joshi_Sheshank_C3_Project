import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    static Restaurant restaurant;
    //Finished - REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE


    @BeforeAll
    public static void init_the_restaurant_object() {
        restaurant = new Restaurant("Taj", "Hyderabad",
                LocalTime.of(10,30),
                LocalTime.of(19,30));

    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //Finished - WRITE UNIT TEST CASE HERE
        // Setting time here for afternoon 1:30 that should return True as it is within the Restaurant Timings
        LocalTime test_time = LocalTime.of(13, 30);
        // Now we use Mockito to mock the current time to the object we just set above
        Restaurant test_res = new Mockito().spy(restaurant);
        // We are returning the mocked test_time object here, so we are faking the local time.
        Mockito.when(((Restaurant) test_res).getCurrentTime()).thenReturn(test_time);
        System.out.println("Method 1 :");
        //This should throw no errors as it is expected to return True.
        System.out.print("Actual Time : ");
        System.out.println(restaurant.getCurrentTime());
        System.out.print("Mocked Time : ");
        System.out.println(test_res.getCurrentTime());
        assertTrue(test_res.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //Finished -  WRITE UNIT TEST CASE HERE
        // Setting time here for afternoon 9.30, that should return False as it is not within the Restaurant Timings
        LocalTime test_time = LocalTime.of(21, 30);
        // Now we use Mockito to mock the current time to the object we just set above
        Restaurant test_res = new Mockito().spy(restaurant);
        // We are returning the mocked test_time object here, so we are faking the local time.
        Mockito.when(((Restaurant) test_res).getCurrentTime()).thenReturn(test_time);
        //This should throw no errors as of now because it should return False as it is expected to return False.
        System.out.println("Method 2 : ");
        System.out.print("Actual Time : ");
        System.out.println(restaurant.getCurrentTime());
        System.out.print("Mocked Time : ");
        System.out.println(test_res.getCurrentTime());
        assertFalse(test_res.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}