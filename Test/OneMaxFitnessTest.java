import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class OneMaxFitnessTest {

    final static Random random = new Random();

    @Test
    void evaluate() {
        for (int i = 0; i < 10000; i++) {
            int minValue = -10000;
            int maxValue = 10000;
            int length = random.nextInt(1000) + 1;
            Solution solution = new Solution(minValue, maxValue, length);
            OneMaxFitness oneMaxFitness = new OneMaxFitness();
            assertEquals(
                    ((double) IntStream.of(solution.getChromosome()).sum()),
                    oneMaxFitness.evaluate(solution)
            );
        }
    }
}