import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class simpleEA implements GeneticAlgorithm{
    private final int populationSize;
    private final int chromosomeLength;
    private final int minValue;
    private final int maxValue;
    private PrintStream outputStream;
    private final Random random = new Random();


    private final FitnessFunction fitnessFunction;
    private final SelectionOperator selectionOperator;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private final TerminationCondition terminationCondition;
    private Population population;
    private int currentGeneration;
    private int numberOfFitnessEvaluations;
    private final ArrayList<Solution> archivePopulation;
    public simpleEA(
            int populationSize,
            int chromosomeLength,
            int minValue,
            int maxValue,
            FitnessFunction fitnessFunction,
            SelectionOperator selectionOperator,
            CrossoverOperator crossoverOperator,
            MutationOperator mutationOperator,
            TerminationCondition terminationCondition,
            PrintStream outputStream) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.fitnessFunction = fitnessFunction;
        this.selectionOperator = selectionOperator;
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.terminationCondition = terminationCondition;
        this.archivePopulation = new ArrayList<>();
        if (outputStream != null) {
            this.outputStream = outputStream;
        }


    }
    @Override
    public void run() {
        this.population = new Population(this.populationSize, this.minValue, this.maxValue, this.chromosomeLength);
        this.outputStream.println("Generation\tNumFitnessEvals\tBestFitness\tAverageFitness\tStandardDeviation");

        while (!this.terminationCondition.isTerminated(this)) {

            this.currentGeneration++;
            this.numberOfFitnessEvaluations += this.population.computeMissingFits(this.fitnessFunction);
            this.population.sortPopulation();
            this.archivePopulation.add(this.population.getSolution(0));
            this.outputStream.println(this.currentGeneration + "\t" + this.numberOfFitnessEvaluations + "\t" + this.population.getBestFitness() + "\t" + this.population.getAverageFitness() + "\t" + this.population.getStdDeviationFitness());

            Population breedingPopulation = selectionOperator.select(this.population, this.populationSize);
            Solution[] offspringSolutions = new Solution[this.populationSize];

            for (int i = 0; i < this.populationSize; i++) {
                int idx1 = random.nextInt(breedingPopulation.getSize());
                int idx2 = random.nextInt(breedingPopulation.getSize());

                while (idx1 == idx2) {
                    idx2 = random.nextInt(breedingPopulation.getSize());
                }

                Solution parent1 = breedingPopulation.getSolution(idx1);
                Solution parent2 = breedingPopulation.getSolution(idx2);
                Solution child = crossoverOperator.crossover(parent1, parent2);
                mutationOperator.mutate(child);
                offspringSolutions[i] = child;
                }
            this.population = new Population(offspringSolutions);
            }
        }
    }
