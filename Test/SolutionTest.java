import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

class SolutionTest {

    final static Random random = new Random();

    @org.junit.jupiter.api.Test
    void constructSolution() {
        for (int i = 0; i < 10000; i++) {
            int minValue = random.nextInt();
            int maxValue = random.nextInt();
            int length = random.nextInt(1000) + 1;

            if (minValue >= maxValue) {
                assertThrows(IllegalArgumentException.class, () -> {
                    Solution _solution = new Solution(minValue, maxValue, length);
                });
                continue;
            }

            Solution solution = new Solution(minValue, maxValue, length);
            for (int j = 0; j < length; j++) {
                assertTrue(solution.getChromosome()[j] >= minValue);
                assertTrue(solution.getChromosome()[j] <= maxValue);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void getLength() {
        for (int i = 0; i < 1000; i++) {
            int minValue = 0;
            int maxValue = 100;
            int length = random.nextInt(1000) + 1;
            Solution solution = new Solution(minValue, maxValue, length);
            assertEquals(length, solution.getLength());
        }

    }

}