package turbo.examples;

import lombok.EqualsAndHashCode;
import turbo.geneticalgorithm.Individual;
import turbo.util.Constants;

import java.util.stream.IntStream;

import static java.lang.Double.valueOf;
import static java.lang.Math.abs;

@EqualsAndHashCode(callSuper = true)
public class WordEvolution extends Individual {
    private final String targetWord;

    private WordEvolution(String target) {
        super(target.length());
        targetWord = target;
    }

    public static WordEvolution towards(String target) {
        return new WordEvolution(target);
    }

    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        IntStream.range(0, getChromosome().getGenes().size()).forEach(i -> word.append(getChromosome().getCharAt(i)));
        return word.toString();
    }

    @Override
    public Individual createRandomOne() {
        return new WordEvolution(targetWord);
    }

    @Override
    public void calculateFitness() {
        setFitnessValue(0d);
        for (int i = 0; i < targetWord.length(); i++) {
            setFitnessValue(getFitnessValue() + getScoreForCharacter(getChromosome().getCharAt(i), targetWord.charAt(i)));
        }
    }

    private Double getScoreForCharacter(char a, char b) {
        Integer diff = abs(a - b);
        return Constants.ALPHABET_SPAN - valueOf(diff);
    }
}
