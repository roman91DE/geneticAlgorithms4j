import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {

        int populationSize = 50;
        int chromosomeLength = 10;
        int minValue = 1;
        int maxValue = 2;
        FitnessFunction fitnessFunction = new ExternalFitnessFunction("/usr/bin/python3", "./src/fitnessFunction.py");
        SelectionOperator selectionOperator = new TournamentSelection(2);
        CrossoverOperator crossoverOperator = new NPointCrossover(2, 0.8d);
        MutationOperator mutationOperator = new RandomMutation(0.05d);
        TerminationCondition terminationCondition = new MaxNumberOfFitnessEvaluations(1_000);
        PrintStream outputStream = System.out;


        GeneticAlgorithm geneticAlgorithm = new simpleGA(
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
