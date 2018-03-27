import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        RandomizedQueue randomizedQueue = new RandomizedQueue();

        String input = StdIn.readLine();
        String[] splitedString = input.split("\\s+");

        for (String s : splitedString) {
            randomizedQueue.enqueue(s);
        }

        Iterator iterator = randomizedQueue.iterator();
        for (int i= 0; i < number; i++) {
            if (iterator.hasNext()) {
                StdOut.println(iterator.next());
            }
        }
    }
}
