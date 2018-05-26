package turbo;

import turbo.examples.WordEvolution;
import turbo.geneticalgorithm.Population;

public class App {
    public static void main(String[] args) {
        Population
                .of(WordEvolution.towards("GENETIC ALGORITHMS ARE TRULY FUN"), 50)
                .evolveThroughGenerations(10000);
    }
}
