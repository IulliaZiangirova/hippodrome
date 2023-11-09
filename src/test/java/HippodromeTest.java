import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {

    @Test
    public void null_list_exception_and_message(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String message = "";
        try {Hippodrome hippodrome = new Hippodrome(null);
        } catch (Exception e){
            message = e.getMessage();
        }
        Assertions.assertEquals(message, "Horses cannot be null.");
    }

    @Test
    public void empty_list_exception_and_message(){
        List<Horse> horses = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        String message = "";
        try {Hippodrome hippodrome = new Hippodrome(horses);
        } catch (Exception e){
            message = e.getMessage();
        }
        Assertions.assertEquals(message, "Horses cannot be empty.");
    }

    @Test
    public void getHorses_test(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i), i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move_should_move_horses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void getWinner_test(){
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Sam", 23, 56);
        Horse horse2 = new Horse("Sam", 4, 3);
        Horse horse3 = new Horse("Sam", 4, 0);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        Assertions.assertEquals(horse1, hippodrome.getWinner());
    }

}
