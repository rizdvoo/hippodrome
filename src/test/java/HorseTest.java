import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class HorseTest {
    private final Horse horse = new Horse("Green", 2.5D, 2.0D);

    @Test
    public void firstArgumentIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(null, anyDouble(), anyDouble())
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "    " })
    public void firstArgumentIsSpace(String str) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse(str, anyDouble(), anyDouble())
        );
        assertEquals( "Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void secondArgumentIsNegative() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse("Any", -5, anyDouble())
        );
        assertEquals( "Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void thirdArgumentIsNegative() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> new Horse("Any", anyDouble(), -5)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameIsUnbroken() {
        assertEquals("Green", horse.getName());
    }

    @Test
    public void getSpeedIsUnbroken() {
        assertEquals(2.5D, horse.getSpeed());
    }

    @Test
    public void getDistanceIsUnbroken() {
        Horse horse2 = new Horse("Range",2.6D);
        assertEquals(2.0D, horse.getDistance());
        assertEquals(0, horse2.getDistance());
    }

    @Test
    void testMove() {
        double initialDistance = 10.0;
        double speed = 5.0;
        double expectedRandomValue = 0.5;

        Horse horse1 = new Horse("Вася", speed, initialDistance);

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(expectedRandomValue);
            horse1.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            double expectedDistance = initialDistance + speed * expectedRandomValue;
            assertEquals(expectedDistance, horse1.getDistance());
        }
    }
}
