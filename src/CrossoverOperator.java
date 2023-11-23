public interface CrossoverOperator {
    public Solution crossover(Solution parent1, Solution parent2) throws IllegalArgumentException;

    default public boolean checkCompatibility(Solution parent1, Solution parent2){
        if (parent1.getChromosome().length != parent2.getChromosome().length)
            return false;
        else if (parent1.getMinValue() != parent2.getMinValue())
            return false;
        else if (parent1.getMaxValue() != parent2.getMaxValue())
            return false;
        return true;
    }
}
