package turbo;

import lombok.Data;

public abstract class Individual {
    public Chromosome chromosome;
    public Double fitnessValue = 0d;

    public Individual(Integer size) {
        chromosome = new Chromosome(size);
    }

    public abstract Individual createClone();

    public abstract void calculateFitness();
}
