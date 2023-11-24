import java.io.OutputStream;
import java.io.PrintStream;

public interface GeneticAlgorithm {

    public void run();
    public int getCurrentGeneration();
    public int getNumberOfFitnessEvaluations();
}
