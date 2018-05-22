package turbo;

public class App {
    public static void main(String[] args) {
        Population
                .of(WordEvolution.towards("ALMIGHTY THOR"), 30)
                .evolveThroughGenerations(100);
    }
}
