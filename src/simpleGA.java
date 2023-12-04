import java.io.PrintStream;
import java.util.Random;

public class simpleGA implements GeneticAlgorithm{
    private final int populationSize;
    private final int chromosomeLength;
    private final int minValue;
    private final int maxValue;
    private PrintStream outputStream;
    private final Random random = new Random();
    private final Stats stats = new Stats();


    private final FitnessFunction fitnessFunction;
    private final SelectionOperator selectionOperator;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private final TerminationCondition terminationCondition;
    private Solution bestSolution, prototypeSolution;
    private int currentGeneration;
    private int numberOfFitnessEvaluations;
    private Population population;
    public simpleGA(
            int populationSize,
            Solution prototypeSolution,
            FitnessFunction fitnessFunction,
            SelectionOperator selectionOperator,
            CrossoverOperator crossoverOperator,
            MutationOperator mutationOperator,
            TerminationCondition terminationCondition,
            PrintStream outputStream) {

        if (prototypeSolution instanceof PermutationSolution) {
            if (!crossoverOperator.permutationIsPreserved()) {
                throw new IllegalArgumentException("Crossover operator does not preserve permutation");
            }
            if (!mutationOperator.permutationIsPreserved()) {
                throw new IllegalArgumentException("Mutation operator does not preserve permutation");
            }
        }

        this.populationSize = populationSize;
        this.chromosomeLength = prototypeSolution.getLength();
        this.minValue = prototypeSolution.getMinValue();
        this.maxValue = prototypeSolution.getMaxValue();
        this.fitnessFunction = fitnessFunction;
        this.selectionOperator = selectionOperator;
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.terminationCondition = terminationCondition;
        this.bestSolution = null;
        this.prototypeSolution = prototypeSolution;
        this.population = null;
        if (outputStream != null) {
            this.outputStream = outputStream;
        }


    }

    @Override
    public int getCurrentGeneration() {
        return this.currentGeneration;
    }

    @Override
    public int getNumberOfFitnessEvaluations() {
        return this.numberOfFitnessEvaluations;
    }

    @Override
    public void plotEvolution() {
        this.stats.plotEvolution();
    }

    private void logStats() {
        this.stats.numberOfGenerations.add(this.currentGeneration);
        this.stats.numberOfFitnessEvaluations.add(this.numberOfFitnessEvaluations);
        this.stats.bestFits.add(population.getBestFitness());
        this.stats.averageFits.add(population.getAverageFitness());
        this.stats.stdDevFits.add(population.getStdDeviationFitness());
    }

    @Override
    public void run() {

        this.population = new Population(this.populationSize, prototypeSolution);
        this.outputStream.println("Generation\tNumFitnessEvals\tBestFitness\tAverageFitness\tStandardDeviation");

        while (!this.terminationCondition.isTerminated(this)) {

            this.currentGeneration++;

            this.numberOfFitnessEvaluations += population.computeMissingFits(this.fitnessFunction);

            population.sortPopulation();

            if (this.bestSolution == null || population.getSolution(0).getFitness() > this.bestSolution.getFitness()) {
                this.bestSolution = population.getSolution(0);
            }

            this.outputStream.println(this.currentGeneration + "\t" + this.numberOfFitnessEvaluations + "\t" + population.getBestFitness() + "\t" + population.getAverageFitness() + "\t" + population.getStdDeviationFitness());
            this.logStats();


            Population breedingPopulation = selectionOperator.select(population, this.populationSize);
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
            population = new Population(offspringSolutions);
            }
        population.computeMissingFits(this.fitnessFunction);
        population.sortPopulation();

        this.outputStream.println(this.bestSolution.toString());
        }
    }
