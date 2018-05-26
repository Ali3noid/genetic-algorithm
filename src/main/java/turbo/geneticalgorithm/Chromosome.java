package turbo.geneticalgorithm;

import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static turbo.util.Constants.ALPHABET_SPAN;
import static turbo.util.Constants.FIRST_CHARACTER;

@Data
public class Chromosome {

    private List<Double> genes;

    Chromosome(Integer size) {
        genes = Stream.generate(new Random()::nextDouble)
                .limit(size)
                .collect(Collectors.toList());
    }

    public char getCharAt(Integer i) {
        return (char) (FIRST_CHARACTER + ALPHABET_SPAN * genes.get(i));
    }
}
