package turbo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.random;
import static turbo.util.Constants.ALPHABET_SPAN;
import static turbo.util.Constants.FIRST_CHARACTER;

@Data
public class Chromosome {

    private List<Double> genes;

    public Chromosome(Integer size) {
        //TODO Find cleaner way to do it
        genes = new ArrayList<>();
        IntStream.range(0, size).forEach(i -> genes.add(random()));
    }

    public char getCharAt(Integer i) {
        return (char) (FIRST_CHARACTER + ALPHABET_SPAN * genes.get(i));
    }
}
