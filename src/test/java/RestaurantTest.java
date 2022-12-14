import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //CO_03
        Restaurant spiedRestaurant=Mockito.spy(restaurant);
        LocalTime setCurrentTime;
        setCurrentTime = LocalTime.parse("11:12:36");
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(setCurrentTime);
        spiedRestaurant.isRestaurantOpen();

        assertTrue(spiedRestaurant.isRestaurantOpen());
        //CO_03
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //CO_04

        Restaurant spiedRestaurant=Mockito.spy(restaurant);
        LocalTime setCurrentTime;
        setCurrentTime = LocalTime.parse("08:12:36");
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(setCurrentTime);
        spiedRestaurant.isRestaurantOpen();
        assertFalse(spiedRestaurant.isRestaurantOpen());
        //CO_04

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

    //<<<<<<<<<<<<<<<<<<<<<<<Price>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//CO_08
    @Test
    public void Display_the_total_order_value_given_the_list_of_items() {

        List<String> cart = new ArrayList<>();
        cart.add("Sweet corn soup");
        cart.add("Vegetable lasagne");
        assertEquals(388,restaurant.calculateBill(cart));
    }
    //CO_08


}