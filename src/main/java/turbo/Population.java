package turbo;

import java.util.ArrayList;

public class Population {
    private Individual precursor;
    private ArrayList<Individual> genePool = new ArrayList<>();

    private Population(Individual precursor, Integer populationSize) {
        this.precursor = precursor;
        createPopulation(populationSize);
    }

    public static Population of(Individual precursor, Integer populationSize) {
        return new Population(precursor, populationSize);
    }

    public void createPopulation(Integer populationSize) {
        Individual individual;
        int target = populationSize - genePool.size();
        if (target < 1) return;

        for (int i = 0; i < target; i++) {
            individual = precursor.createClone();

            genePool.add(individual);
        }
    }
}
