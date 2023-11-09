import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void null_name_exception_and_message(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2));
        String message = "";
        try {Horse horse = new Horse(null, 2);
        } catch (Exception e){
            message = e.getMessage();
        }
        Assertions.assertEquals(message, "Name cannot be null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t\t", "\r", "\n", "\f"})
    public void whitespace_string_name_exception_and_message(String string){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(string, 2));
        String message = "";
        try {Horse horse = new Horse(string, 2);
        } catch (Exception e){
            message = e.getMessage();
        }
        Assertions.assertEquals(message, "Name cannot be blank.");
    }

    @Test
    public void negative_speed_exception_and_message(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Sam", -3));
        String message = "";
        try {Horse horse = new Horse("Sam", -3);
        } catch (Exception e){
            message = e.getMessage();
        }
        Assertions.assertEquals(message, "Speed cannot be negative.");
    }

    @Test
    public void negative_distance_exception_and_message(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Sam", 3, -3));
        String message = "";
        try {Horse horse = new Horse("Sam", 3, -3);
        } catch (Exception e){
            message = e.getMessage();
        }
        Assertions.assertEquals(message, "Distance cannot be negative.");
    }


    @Test
    public void should_get_name(){
        Horse horse = new Horse("Sam", 2);
        Assertions.assertEquals("Sam", horse.getName());
    }

    @Test
    public void should_get_speed(){
        Horse horse = new Horse("Sam", 2);
        Assertions.assertEquals(2, horse.getSpeed());
    }

    @Test
    public void should_get_distance(){
        Horse horseWithDistance = new Horse("Sam", 2, 4);
        Horse horseWithoutDistance = new Horse("Sam", 2);
        Assertions.assertEquals(4, horseWithDistance.getDistance());
        Assertions.assertEquals(0, horseWithoutDistance.getDistance());
    }



    @Test
    public void move_should_call_getRandomDouble(){
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Sam", 2, 3).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
        }

    @Test
    public void move_calculate_distance(){
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            mockedStatic.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.562);
            Horse horse = new Horse("Sam", 2, 3);
            horse.move();
            double expectedDistance = 3 + 2 * 0.562;
            Assertions.assertEquals(expectedDistance, horse.getDistance());
        }
    }
    }







