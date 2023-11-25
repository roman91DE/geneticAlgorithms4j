import java.util.Random;

public class SwapMutation implements MutationOperator{
    private final double mutationProbability;
    private final Random random = new Random();
    public SwapMutation(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    @Override
    public boolean permutationIsPreserved() {
        return true;
    }
    @Override
    public void mutate(Solution solution) {
        int[] chromosome = solution.getChromosome();
        for (int i = 0; i < chromosome.length; i++) {
            if (this.random.nextDouble() < this.mutationProbability) {
                int j = this.random.nextInt(chromosome.length);
                int temp = chromosome[i];
                chromosome[i] = chromosome[j];
                chromosome[j] = temp;
            }

        }
    }
}
