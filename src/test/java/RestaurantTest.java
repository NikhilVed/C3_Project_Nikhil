import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    int mockedcost;
    List<Item> items;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void beforeEachSetup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        restaurant.displayDetails();
    }

//    @AfterEach
//    public void afterEachSetup(){
//        restaurant.displayDetails();
//    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime workingTime = LocalTime.parse("12:10:11");
        Restaurant spyRestaurant = Mockito.spy(restaurant);

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(workingTime);

        assertTrue(spyRestaurant.isRestaurantOpen());


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        LocalTime workingTime = LocalTime.parse("23:10:11");
        Restaurant spyRestaurant = Mockito.spy(restaurant);

        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(workingTime);

        assertFalse(spyRestaurant.isRestaurantOpen());

    }

    @Test
    public void selecting_one_or_more_items_from_menu_should_return_total_cost_of_selected_items(){

        items =  new ArrayList<Item>();

        Item oneItem = restaurant.getMenu().get(0);
        Item twoItem =restaurant.getMenu().get(1);
//        twoItem.toString();
        items.add(oneItem);
        Item spyitem1 = Mockito.spy(oneItem);
        Mockito.when(spyitem1.getPrice()).thenReturn(119);
        mockedcost = spyitem1.getPrice();

        System.out.println("One Item Selected"+restaurant.getMenu().get(0)+" and price of that item "+mockedcost);
        assertEquals(oneItem.getPrice(),restaurant.getTotalAmountItems(items));

        items.add(twoItem);
        Item spyitem2 = Mockito.spy(twoItem);
        Mockito.when(spyitem2.getPrice()).thenReturn(269);
        mockedcost = spyitem1.getPrice() + spyitem2.getPrice();
        System.out.println("Multiple Item Selected"+items+" and mocked amount"+mockedcost);
        assertEquals(mockedcost,restaurant.getTotalAmountItems(items));


    }

    @Test
    public void selecting_no_item_from_menu_should_return_total_cost_as_zero() throws itemNotFoundException {

        items =  new ArrayList<Item>();
        System.out.println("Items present" + items);
        mockedcost = 0;
        assertEquals(mockedcost,restaurant.getTotalAmountItems(items));


    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}