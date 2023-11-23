import java.util.Random;

public class TournamentSelection implements SelectionOperator{
    private final int tournamentSize;
    private static final Random random = new Random();
    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Population select(Population population, int count) {
        Solution[] selectedSolutions = new Solution[count];
        for (int i = 0; i < count; i++) {
            Solution[] tournamentPool = new Solution[this.tournamentSize];
            for (int j = 0; j < this.tournamentSize; j++) {
                tournamentPool[j] = population.getSolution(random.nextInt(population.getSize()));
            }
            Population tournament = new Population(tournamentPool);
            tournament.sortPopulation();
            selectedSolutions[i] = tournament.getSolution(0);
        }
        return new Population(selectedSolutions);
    }



}

