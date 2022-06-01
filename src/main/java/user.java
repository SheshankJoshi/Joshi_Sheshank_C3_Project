import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class user {
    public List <Item> items_chosen = new ArrayList<Item>(); // Where we store the list of the items
    public int total_price=0;
    private int userID=0;
    private static int count=0;

    public void user() {
        this.userID = createUserID();
    }
    private int createUserID() {
        count = count+1;
        System.out.print("Count : ");
        System.out.println(count);
        return count;
    }
    public int getUserID() {return this.userID;}

    public int add_to_order(Item item) {
        items_chosen.add(item);
        //System.out.print("Item Added : ");
        //System.out.println(item);
        total_price = total_price + item.getPrice();
        return total_price;
    }
    public int get_total() {
        return  total_price;
    }
    public int get_total_price(@NotNull List<Item> itemList) {
        int total=0;
        for (Item i : itemList) {
            total = total + i.getPrice();
        }
        return total;
    }
}
