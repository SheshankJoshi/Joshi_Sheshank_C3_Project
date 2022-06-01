import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*Please Remember that this is not a black box testing. Run each method separately to check if its working.*/

class userTest {
    public static Restaurant restaurant;
    Item item;
    public static ArrayList <Item> test_items = new ArrayList<Item>();
    public user current_user=new user();
    /*NOTE - As given in the problem statement, the item that is given or added to the order list is already assumed to be present in the
     menu of the restaurant. Hence, the fail case scenario need not be checked. So, here it is assumed to be Taj and it is fixed. That's why the test variable is taken static.*/

  @BeforeAll
    public static void init_Restaurant_object_with_menu_items() {
        // Initialize the Restaurant Object with appropriate initializing
        restaurant = new Restaurant("Taj", "Hyderabad",
                LocalTime.of(10,30),
                LocalTime.of(19,30));
        // Add items to the Restaurant Menu
        Item item1 = new Item("Panipuri", 20);
        test_items.add(item1);
        restaurant.addToMenu(item1.getName(), item1.getPrice());
        //
        Item item2 = new Item("Dahi Wada",45);
        test_items.add(item2);
        restaurant.addToMenu(item2.getName(), item2.getPrice());
        //
        Item item3 = new Item("Veg Biryani",200);
        restaurant.addToMenu(item3.getName(), item3.getPrice());
        test_items.add(item3);
        //
        Item item4 = new Item("Double-ka-meetha",65);
        restaurant.addToMenu(item4.getName(), item4.getPrice());
        test_items.add(item4);
        //
        Item item5 = new Item("Chicken Tikka",200);
        restaurant.addToMenu(item5.getName(), item5.getPrice());
        test_items.add(item5);
        //
        Item item6 = new Item("Aloo Paratha",120);
        restaurant.addToMenu(item6.getName(), item6.getPrice());
        test_items.add(item6);
        //
        Item item7 = new Item("Jeera Rice",180);
        restaurant.addToMenu(item7.getName(), item7.getPrice());
        test_items.add(item7);
        //
        Item item8 = new Item("Matar Paneer",280);
        restaurant.addToMenu(item8.getName(), item8.getPrice());
        test_items.add(item8);
        //
        Item item9 = new Item("Veg Frankie",80);
        restaurant.addToMenu(item9.getName(), item9.getPrice());
        test_items.add(item9);
        Item item10 = new Item("Paav Bhaji",75);
        restaurant.addToMenu(item10.getName(), item10.getPrice());
        test_items.add(item10);
    }

    @Test
    public void test_if_price_properly_calculated_should_return_true() {
        //current_user = new user();
        current_user.add_to_order(test_items.get(1)); //Adding Dahi Wada to the order
        current_user.add_to_order(test_items.get(3)); //Adding Double-Ka-Meeta to the order
        /*We are tyring to add two items from the restaurant */
        assertEquals(110,current_user.get_total()); // Total Cost is 110
    }

    @Test
    public void test_if_price_improperly_calculated_should_return_false() {
        //current_user = new user();
        current_user.add_to_order(test_items.get(0)); //Adding Aloo Paratha to the order. So Total now costs 130
        //current_user.add_to_order(test_items.get(3)); //Adding Double-Ka-Meeta to the order
        /*We are tyring to add only one items from the restaurant, but calculating total bill for two items i.e. Panipuri and Double-ka-Meeta */
        assertNotEquals(85,current_user.get_total()); // Total Cost is 85 here but actual cost is 20 since we now have only one item
        System.out.println(current_user.items_chosen);
    }

    @Test
    public void test_if_items_added_properly_should_return_true_since_added() {
        // Checking if the first item we added to the user is Panipuri or not.
        current_user.add_to_order(test_items.get(9)); //Adding Paav Bhaji to the order.
        current_user.add_to_order(test_items.get(2)); //Adding Veg Biryani to the order.
        assertEquals("Paav Bhaji",current_user.items_chosen.get(0).getName());
    }

    @Test
    public void test_if_items_added_properly_should_return_false_since_not_added() {
        // Checking if the first item we added to the user is Dahi-Wada or not.
        current_user.add_to_order(test_items.get(5)); //Adding Aloo Paratha to the order.
        current_user.add_to_order(test_items.get(6)); //Adding Jeera Rice to the order.
        assertNotEquals("Dahi Wada",current_user.items_chosen.get(1).getName());
        // The above return False because we have ordered Pani puri as the first order.
    }

    // You can Ignore this method, since its not that important.
    @Test
    public void test_if_userID_properly_created() {
        user user1 = new user(); // User 2 Created
        user user2 = new user(); // User 3 Created
        assertEquals(2,user2.getUserID());
        assertEquals(1,user1.getUserID());
    }

}