import java.util.stream.IntStream;

public class OneMaxFitness implements FitnessFunction{
    @Override
    public double evaluate(Solution solution) {
        return ((double) IntStream.of(solution.getChromosome()).sum());
    }
}
