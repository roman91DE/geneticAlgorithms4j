public class MaxNumberOfGenerations implements TerminationCondition{
    private final int maxGenerations;
    public MaxNumberOfGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }
    @Override
    public boolean isTerminated(GeneticAlgorithm geneticAlgorithm) {
        return geneticAlgorithm.getCurrentGeneration() >= this.maxGenerations;
    }
}
