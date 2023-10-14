import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @Test
    public void argumentIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void argumentIsEmpty() {
        List<Horse> horseList = new ArrayList<>();
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(horseList)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorsesIsUnbroken(){
        List<Horse> horseList = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            Horse horse = new Horse("Horse" + i, 2.5D, 1.5D);
            horseList.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horseList);

        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    public void getWinnerIsUnbroken() {
        List<Horse> horseList = new ArrayList<>();
        Horse rich = new Horse("Rich", 3.1D, 3.4D);
        horseList.add(rich);

        for (int i = 1; i <= 30; i++) {
            Horse horse = new Horse("Horse" + i, 2.5D, 1.5D);
            horseList.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(rich, hippodrome.getWinner());
    }


    @Test
    void testRace() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);

        hippodrome.move();

        for (Horse horse : hippodrome.getHorses()) {
            verify(horse, times(1)).move();
        }
    }
}
