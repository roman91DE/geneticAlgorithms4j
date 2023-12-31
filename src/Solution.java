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

    public Solution(int[] chromosome, Solution prototype) {
        this.minValue = prototype.getMinValue();
        this.maxValue = prototype.getMaxValue();
        this.chromosome = chromosome;
        this.fitness = null;
    }

    public int getLength() {
        return this.chromosome.length;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }

    public void setChromosome(int[] chromosome) {
        System.arraycopy(chromosome, 0, this.chromosome, 0, chromosome.length);
    }

    public double getFitness() throws IllegalStateException{
        if (this.fitness == null) {
            throw new IllegalStateException("Fitness has not been computed yet");
        }
        return this.fitness;
    }

    public boolean fitnessIsComputed() {
        return this.fitness != null;
    }

    public int getMinValue() {
        return this.minValue;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public void computeFitness(FitnessFunction fitnessFunction) {
        this.fitness = fitnessFunction.evaluate(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solution=[");
        for (int i = 0; i < this.getLength(); i++) {
            sb.append(this.chromosome[i]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]\n");
        if (this.fitness != null) {
            sb.append("Fitness=").append(this.fitness.toString());
        }
        return sb.toString();
    }

}