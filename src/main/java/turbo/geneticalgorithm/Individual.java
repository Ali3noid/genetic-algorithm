package turbo.geneticalgorithm;

import lombok.Data;

@Data
public abstract class Individual {
    private Chromosome chromosome;
    private Double fitnessValue = 0d;

    public Individual(Integer size) {
        chromosome = new Chromosome(size);
    }

    public abstract Individual createRandomOne();

    public abstract void calculateFitness();
}
