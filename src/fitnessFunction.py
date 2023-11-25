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
