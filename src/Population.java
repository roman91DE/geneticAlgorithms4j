import java.util.Arrays;

public class Population {

    private final Solution[] solutions;

    public Population(int size, Solution prototypeSolution) {
        this.solutions = new Solution[size];

        if (prototypeSolution instanceof PermutationSolution) {
            for (int i = 0; i < size; i++) {
                this.solutions[i] = new PermutationSolution(prototypeSolution.getMinValue(), prototypeSolution.getMaxValue(), prototypeSolution.getLength());
            }
        } else {
            for (int i = 0; i < size; i++) {
                this.solutions[i] = new Solution(prototypeSolution.getMinValue(), prototypeSolution.getMaxValue(), prototypeSolution.getLength());
            }
        }
    }

    public Population(Solution[] solutions) {
        this.solutions = solutions;
    }

    public Solution[] getSolutions() {
        return solutions;
    }

    public void sortPopulation() {
        Arrays.sort(this.solutions, (a, b) -> {
            return Double.compare(b.getFitness(), a.getFitness());
        });
    }

    public Solution getSolution(int index) {
        return this.solutions[index];
    }

    public int getSize() {
        return this.solutions.length;
    }

    public int computeMissingFits(FitnessFunction fitnessFunction) {
        int numberOfFitnessEvaluations = 0;
        for (Solution solution : this.solutions) {
            if (!solution.fitnessIsComputed()) {
                solution.computeFitness(fitnessFunction);
                numberOfFitnessEvaluations++;
            }
        }
        return numberOfFitnessEvaluations;
    }

    public double getAverageFitness() {
        double sum = 0;
        for (Solution solution : this.solutions) {
            sum += solution.getFitness();
        }
        return sum / this.solutions.length;
    }

    public double getStdDeviationFitness() {
        double average = this.getAverageFitness();
        double sum = 0;
        for (Solution solution : this.solutions) {
            sum += Math.pow(solution.getFitness() - average, 2);
        }
        return Math.sqrt(sum / this.solutions.length);
    }

    public double getBestFitness() {
        return this.solutions[0].getFitness();
    }

}
