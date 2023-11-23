import java.util.ArrayList;
public class simpleEA implements GeneticAlgorithm{
    private final int populationSize;
    private final int chromosomeLength;
    private final int minValue;
    private final int maxValue;
    private final int maxGenerations;
    private final FitnessFunction fitnessFunction;
    private final SelectionOperator selectionOperator;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private Population population;
    private int generation;
    private ArrayList<Solution> archivePopulation;
    public simpleEA(
            int populationSize,
            int chromosomeLength,
            int minValue,
            int maxValue,
            int maxGenerations,
            FitnessFunction fitnessFunction,
            SelectionOperator selectionOperator,
            CrossoverOperator crossoverOperator,
            MutationOperator mutationOperator) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.maxGenerations = maxGenerations;
        this.fitnessFunction = fitnessFunction;
        this.selectionOperator = selectionOperator;
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.generation = 0;
        this.archivePopulation = new ArrayList<>();

    }
    @Override
    public void run() {
        this.population = new Population(this.populationSize, this.chromosomeLength, this.minValue, this.maxValue);
        while (this.generation < this.maxGenerations) {
            this.generation++;
        }
    }
}
