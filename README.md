# GeneticAlgorithms4j

## Description

This is a simple library for genetic algorithms in Java. It is designed to be easy to use and to be easily extensible.

## Usage

### Running a simple genetic algorithm

The following code shows how to run a simple genetic algorithm built with some sample components included in the library.

```java
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        
        // Provide the 3 basic components that describe a candidate solution
        int chromosomeLength = 50;
        int minValue = 0;
        int maxValue = 1;
        // define the size of the population
        int populationSize = 1000;
        // define the fitness function
        FitnessFunction fitnessFunction = new OneMaxFitness();
        // define the genetic operators
        SelectionOperator selectionOperator = new TournamentSelection(2);
        CrossoverOperator crossoverOperator = new NPointCrossover(2, 0.8d);
        MutationOperator mutationOperator = new RandomMutation(0.05d);
        TerminationCondition terminationCondition = new MaxNumberOfFitnessEvaluations(1_000_000);
        // pass an output stream to print the results
        PrintStream outputStream = System.out;

        // create the genetic algorithm from the components
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
        
        // run the genetic algorithm
        geneticAlgorithm.run();


    }
}
```
