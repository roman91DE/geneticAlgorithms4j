# GeneticAlgorithms4j

## Description

This is a simple library for genetic algorithms in Java. It is designed to be easy to use and to be easily extensible.

## Usage

### Running a simple genetic algorithm

The following code shows how to run a simple genetic algorithm built with some sample components included in the library.

```java
import com.geneticalgorithms4j.*;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        
        // define the 3 basic components that describe a candidate solution
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

### Using an external program as a fitness function 

The following code shows how to run a genetic algorithm using an external python program as a fitness function.
This is useful if you want to tune a machine learning model implemented in a slower scripting language.

```java
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
```

The corresponding python program can be implemented as the following:
Read the chromosome from the standard input and print the fitness value to the standard output.

```python
import argparse
from functools import reduce
from typing import List
from sys import stdout

"""
Synopsis: Fitness function for genetic algorithms

Description: This is an example fitness function for genetic algorithms. It takes a list of integers
representing a chromosome and returns a float representing the fitness of the chromosome. The fitness
function is the product of all the integers in the chromosome. This fitness function is used in the


Usage: python3 fitnessFunction.py <chromosome>
"""

def fitnessFunction(chromosome: List[int]) -> float:
    """ Example Fitness function for genetic algorithms """
    return float(reduce(lambda x, y: x * y, chromosome))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Fitness function for genetic algorithms')
    parser.add_argument('chromosome', metavar='chromosome', type=int, nargs='+',
                        help='a space separated list of integers representing the chromosome')
    args = parser.parse_args()
    return_value = fitnessFunction(args.chromosome)

    stdout.write(str(return_value))
    stdout.write('\n')
    stdout.flush()
```
