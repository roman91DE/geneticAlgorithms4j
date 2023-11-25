import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PermutationSolution extends Solution{
    public PermutationSolution(int minValue, int maxValue, int length) throws IllegalArgumentException {
        super(minValue, maxValue, length);

        for (int i = this.getMinValue(); i <= this.getMaxValue(); i++) {
            this.getChromosome()[i - this.getMinValue()] = i;
        }
        List<Integer> list = IntStream.of(this.getChromosome()).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        this.setChromosome(list.stream().mapToInt(Integer::intValue).toArray());
    }

    public PermutationSolution(int[] chromosome, Solution prototype) {
        super(chromosome, prototype);
    }

    public boolean isValidPermutation() {
        int sum = IntStream.of(this.getChromosome()).sum();
        int expectedSum = (this.getMaxValue() - this.getMinValue() + 1) * (this.getMaxValue() - this.getMinValue()) / 2;
        return sum == expectedSum;
    }

}
