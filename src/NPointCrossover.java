import java.util.Arrays;

public class NPointCrossover implements CrossoverOperator{
    private final int n;
    public NPointCrossover(int n) throws IllegalArgumentException{
        if (n < 1){
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
    }
    @Override
    public Solution crossover(Solution parent1, Solution parent2) throws IllegalArgumentException{

        if (!this.checkCompatibility(parent1, parent2)){
            throw new IllegalArgumentException("Parents are not compatible");
        }

        int[] chromosome1 = parent1.getChromosome();
        int[] chromosome2 = parent2.getChromosome();
        int[] childChromosome = new int[chromosome1.length];
        int[] crossoverPoints = new int[n];
        for(int i = 0; i < n; i++){
            crossoverPoints[i] = (int) (Math.random() * chromosome1.length);
        }
        Arrays.sort(crossoverPoints);
        int crossoverIndex = 0;
        for(int i = 0; i < chromosome1.length; i++){
            if(crossoverIndex < n && i > crossoverPoints[crossoverIndex]){
                crossoverIndex++;
            }
            if(crossoverIndex % 2 == 0){
                childChromosome[i] = chromosome1[i];
            }else{
                childChromosome[i] = chromosome2[i];
            }
        }
        return new Solution(childChromosome, parent1);
    }
}
