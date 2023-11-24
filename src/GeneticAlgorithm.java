import java.io.OutputStream;
import java.io.PrintStream;

public interface GeneticAlgorithm {
    public int currentGeneration = 0;
    public int numberOfFitnessEvaluations = 0;
    public PrintStream outputStream = System.out;



    public void run();
}
