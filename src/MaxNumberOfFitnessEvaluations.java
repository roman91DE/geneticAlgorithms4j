public class MaxNumberOfFitnessEvaluations implements TerminationCondition{
    private final int maxNumberOfFitnessEvaluations;
    public MaxNumberOfFitnessEvaluations(int maxNumberOfFitnessEvaluations) {
        this.maxNumberOfFitnessEvaluations = maxNumberOfFitnessEvaluations;
    }
    @Override
    public boolean isTerminated(GeneticAlgorithm geneticAlgorithm) {
        return geneticAlgorithm.numberOfFitnessEvaluations >= this.maxNumberOfFitnessEvaluations;
    }
}
