import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {

        int populationSize = 500;
        Solution prototypeSolution = new Solution(0, 9, 10);
        FitnessFunction fitnessFunction = new OneMaxFitness();
        SelectionOperator selectionOperator = new TournamentSelection(2);
        CrossoverOperator crossoverOperator = new NPointCrossover(1,0.8d);
        MutationOperator mutationOperator = new RandomMutation(0.05d);
        TerminationCondition terminationCondition = new MaxNumberOfGenerations(100);
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
