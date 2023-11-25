import java.util.Arrays;
import java.util.Random;

public class OrderCrossover implements CrossoverOperator{
    private final Random random = new Random();
    private final double crossoverProbability;
    public OrderCrossover(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
    }

    @Override
    public boolean permutationIsPreserved() {
        return true;
    }

    @Override
    public Solution crossover(Solution parent1, Solution parent2) {
        int invalidValue = parent1.getMinValue() - 1;
        int[] parent1Chromosome = parent1.getChromosome();
        int[] parent2Chromosome = parent2.getChromosome();
        int[] childChromosome = new int[parent1Chromosome.length];
        if (this.random.nextDouble() < this.crossoverProbability) {
            int cutPoint1 = this.random.nextInt(parent1Chromosome.length);
            int cutPoint2 = this.random.nextInt(parent1Chromosome.length);
            int minCutPoint = Math.min(cutPoint1, cutPoint2);
            int maxCutPoint = Math.max(cutPoint1, cutPoint2);
            Arrays.fill(childChromosome, invalidValue);
            if (maxCutPoint + 1 - minCutPoint >= 0)
                System.arraycopy(parent1Chromosome, minCutPoint, childChromosome, minCutPoint, maxCutPoint + 1 - minCutPoint);
            int j = 0;
            for (int i = 0; i < childChromosome.length; i++) {
                if (childChromosome[i] == invalidValue) {
                    while (j < parent2Chromosome.length) {
                        if (!contains(childChromosome, parent2Chromosome[j])) {
                            childChromosome[i] = parent2Chromosome[j];
                            j++;
                            break;
                        }
                        j++;
                    }
                }
            }
        } else {
            System.arraycopy(parent1Chromosome, 0, childChromosome, 0, childChromosome.length);
        }
        return new Solution(childChromosome, parent1);
    }

    private boolean contains(int[] array, int value) {
        for (int j : array) {
            if (j == value) return true;
        }
        return false;
    }
}
