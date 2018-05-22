package turbo;

public abstract class Individual {
    public Chromosome chromosome;
    private Double fitnessValue;

    public Individual(Integer size) {
        chromosome = new Chromosome(size);
    }

    public abstract Individual createClone();
}
