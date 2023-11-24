import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {

        int populationSize = 500;
        int chromosomeLength = 10;
        int minValue = 0;
        int maxValue = 1;
        FitnessFunction fitnessFunction = new OneMaxFitness();
        SelectionOperator selectionOperator = new TournamentSelection(2);
        CrossoverOperator crossoverOperator = new NPointCrossover(2, 0.8d);
        MutationOperator mutationOperator = new RandomMutation(0.05d);
        TerminationCondition terminationCondition = new MaxNumberOfFitnessEvaluations(10_000);
        PrintStream outputStream = System.out;


        GeneticAlgorithm geneticAlgorithm = new simpleEA(
                populationSize,
                chromosomeLength,
                minValue,
                maxValue,
                fitnessFunction,
                selectionOperator,
                crossoverOperator,
                mutationOperator,
                terminationCondition,
                outputStream);

        geneticAlgorithm.run();


    }
}
