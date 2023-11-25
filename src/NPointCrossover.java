import java.util.Arrays;
import java.util.Random;

public class NPointCrossover implements CrossoverOperator {
    private final int n;
    private final double crossoverRate;
    private final Random random = new Random();

    public NPointCrossover(int n, double crossoverRate) throws IllegalArgumentException {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (crossoverRate < 0 || crossoverRate > 1) {
            throw new IllegalArgumentException("crossoverRate must be between 0 and 1");
        }
        this.n = n;
        this.crossoverRate = crossoverRate;
    }

    @Override
    public boolean permutationIsPreserved() {
        return false;
    }

    @Override
    public Solution crossover(Solution parent1, Solution parent2) throws IllegalArgumentException {

        if (!this.checkCompatibility(parent1, parent2)) {
            throw new IllegalArgumentException("Parents are not compatible");
        }
        if (this.random.nextDouble() > this.crossoverRate) {
            if (this.random.nextBoolean()) {
                return new Solution(parent2.getChromosome(), parent2);
            } else {
                return new Solution(parent1.getChromosome(), parent1);
            }
        }

        int[] chromosome1 = parent1.getChromosome();
        int[] chromosome2 = parent2.getChromosome();
        int[] childChromosome = new int[chromosome1.length];
        int[] crossoverPoints = new int[n];
        for (int i = 0; i < n; i++) {
            crossoverPoints[i] = (int) (Math.random() * chromosome1.length);
        }
        Arrays.sort(crossoverPoints);
        int crossoverIndex = 0;
        for (int i = 0; i < chromosome1.length; i++) {
            if (crossoverIndex < n && i > crossoverPoints[crossoverIndex]) {
                crossoverIndex++;
            }
            if (crossoverIndex % 2 == 0) {
                childChromosome[i] = chromosome1[i];
            } else {
                childChromosome[i] = chromosome2[i];
            }
        }
        return new Solution(childChromosome, parent1);
    }
}
