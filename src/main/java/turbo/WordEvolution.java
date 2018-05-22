package turbo;

import java.util.stream.IntStream;

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
}
