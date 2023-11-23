import java.util.Arrays;

public class Population {
    private final Solution[] solutions;

    public Population(int size, int minValue, int maxValue, int length) {
        this.solutions = new Solution[size];
        for (int i = 0; i < size; i++) {
            this.solutions[i] = new Solution(minValue, maxValue, length);
        }
    }

    public Population(Solution[] solutions) {
        this.solutions = solutions;
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
}
