public class maxGenerations implements TerminationCondition{
    private final int maxGenerations;
    public maxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }
    @Override
    public boolean isTerminated(GeneticAlgorithm geneticAlgorithm) {
        return geneticAlgorithm.currentGeneration >= this.maxGenerations;
    }
}
