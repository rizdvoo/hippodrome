import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {
    @Test
    @Disabled
    public void mainTest() {
        assertTimeout(
                ofSeconds(22),
                () -> Main.main(new String[]{})
        );
    }
}
