import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalFitnessFunction implements FitnessFunction{
    private final String fileName, executableName;
    public ExternalFitnessFunction(String executableName, String fileName) {
        this.fileName = fileName;
        this.executableName = executableName;
    }
    @Override
    public double evaluate(Solution solution) {
        int[] chromosome = solution.getChromosome();

        String[] command = new String[2 + chromosome.length];
        command[0] = this.executableName;
        command[1] = this.fileName;
        for (int i = 0; i < chromosome.length; i++) {
            command[i + 2] = Integer.toString(chromosome[i]);
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.waitFor();
            String line;
            while ((line = stdError.readLine()) != null) {
                System.err.println(line);
            }
            String returnVal = stdOut.readLine();
            return Double.parseDouble(returnVal);
        } catch (IOException  e) {
            e.printStackTrace();
            return 0;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
