import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {

        int populationSize = 50;
        Solution prototypeSolution = new PermutationSolution(0, 9, 10);
        FitnessFunction fitnessFunction = new OneMaxFitness();
        SelectionOperator selectionOperator = new TournamentSelection(2);
        CrossoverOperator crossoverOperator = new OrderCrossover(0.8d);
        MutationOperator mutationOperator = new SwapMutation(0.05d);
        TerminationCondition terminationCondition = new MaxNumberOfFitnessEvaluations(1_000);
        PrintStream outputStream = System.out;


        GeneticAlgorithm geneticAlgorithm = new simpleGA(
                populationSize,
                prototypeSolution,
                fitnessFunction,
                selectionOperator,
                crossoverOperator,
                mutationOperator,
                terminationCondition,
                outputStream);

        geneticAlgorithm.run();


    }
}
