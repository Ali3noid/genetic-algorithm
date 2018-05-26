package turbo.geneticalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Math.random;
import static java.util.Comparator.comparing;

public class Population {
    private final Individual precursor;
    private ArrayList<Individual> genePool = new ArrayList<>();
    private int populationSize = 30;
    private double mutationRate = 0.2;

    private Population(Individual precursor) {
        this.precursor = precursor;
        createPopulation();
    }

    public static Population of(Individual precursor) {
        return new Population(precursor);
    }

    public Population withSizeOf(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public Population withMutationRateOf(double mutationRate) {
        this.mutationRate = mutationRate;
        return this;
    }

    private void createPopulation() {
        for (int i = 0; i < populationSize; i++) {
            genePool.add(precursor.createRandomOne());
        }
    }

    public void evolveThroughGenerations(Integer numberOfGenerations) {
        for (int i = 0; i < numberOfGenerations; i++) {
            genePool.forEach(Individual::calculateFitness);

            ArrayList<Individual> newPool = new ArrayList<>();

            Individual elite = Collections.max(genePool, comparing(Individual::getFitnessValue));
            System.out.println("Generation " + i + ": " + elite + ", " + elite.getFitnessValue());
            newPool.add(elite);
            for (int j = 0; j < populationSize - 1; j++) {

                Individual child = breedChild();
                newPool.add(child);
            }
            genePool = newPool;
        }
    }

    private Individual breedChild() {
        Double fitnessSum = genePool.stream()
                .mapToDouble(Individual::getFitnessValue)
                .sum();
        Individual firstParent = drawIndividual(fitnessSum);
        Individual secondParent = drawIndividual(fitnessSum);

        Individual child = cross(firstParent, secondParent);
        mutate(child);
        return child;
    }

    private void mutate(Individual child) {
        if (random() <= mutationRate) {
            Integer size = precursor.getChromosome().getGenes().size();
            Integer index = new Random().ints(0, size).findFirst().getAsInt();
            child.getChromosome().getGenes().set(index, random());
        }
    }

    private Individual drawIndividual(Double fitnessSum) {
        double remainder = Math.random() * fitnessSum;
        for (Individual individual : genePool) {
            remainder -= individual.getFitnessValue();
            if (remainder <= 0.0) {
                return individual;
            }
        }
        return genePool.get(genePool.size() - 1);
    }

    private Individual cross(Individual firstParent, Individual secondParent) {
        //TODO extract those 'fluent snakes' into methods
        Integer crossoverPoint = new Random().ints(1, precursor.getChromosome().getGenes().size()).findFirst().getAsInt();
        List<Double> firstPart = firstParent.getChromosome().getGenes().stream()
                .limit(crossoverPoint)
                .collect(Collectors.toList());

        List<Double> secondPart = secondParent.getChromosome().getGenes().stream()
                .skip(crossoverPoint)
                .collect(Collectors.toList());

        firstPart.addAll(secondPart);

        Individual child = precursor.createRandomOne();
        child.getChromosome().setGenes(firstPart);

        return child;
    }
}
