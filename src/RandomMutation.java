import java.util.Random;

public class RandomMutation implements MutationOperator{
    private final static Random random = new Random();
    private final double mutationRate;

    public RandomMutation(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public void mutate(Solution solution) {

        int minValue = solution.getMinValue();
        int maxValue = solution.getMaxValue();

        for (int i = 0; i < solution.getLength(); i++) {
            if (random.nextDouble() < this.mutationRate) {
                solution.getChromosome()[i] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
    }
}
