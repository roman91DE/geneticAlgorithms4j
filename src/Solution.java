import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Solution {

    private static final Random random = new Random();
    private final int minValue;
    private final int maxValue;
    private final int[] chromosome;
    private Double fitness;

    public Solution(int minValue, int maxValue, int length) throws IllegalArgumentException{
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be less than maxValue");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;

        this.chromosome = new int[length];
        for (int i = 0; i < length; i++) {
            this.chromosome[i] = ThreadLocalRandom.current().nextInt(this.minValue, this.maxValue + 1);
        }

        this.fitness = null;
    }

    public int getLength() {
        return this.chromosome.length;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.getLength(); i++) {
            sb.append(this.chromosome[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

}