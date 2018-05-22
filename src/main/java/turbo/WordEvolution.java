package turbo;

import turbo.util.Constants;

import java.util.stream.IntStream;

import static java.lang.Double.valueOf;
import static java.lang.Math.abs;

public class WordEvolution extends Individual {
    public String targetWord;

    private WordEvolution(String target) {
        super(target.length());
        targetWord = target;
    }

    public static Individual towards(String target) {
        return new WordEvolution(target);
    }

    public String toString() {
        StringBuilder word = new StringBuilder();
        IntStream.range(0, chromosome.getGenes().size()).forEach(i -> word.append(chromosome.getCharAt(i)));
        return word.toString();
    }

    @Override
    public Individual createClone() {
        return new WordEvolution(targetWord);
    }

    @Override
    public void calculateFitness() {
        for (int i = 0; i < targetWord.length(); i++) {
            fitnessValue += getScoreForCharacter(chromosome.getCharAt(i), targetWord.charAt(i));
        }
    }

    private Double getScoreForCharacter(char a, char b) {
        Integer diff = abs(a - b);
        return Constants.ALPHABET_SPAN - valueOf(diff);
    }
}
