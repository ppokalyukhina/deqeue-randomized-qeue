import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

       while (!StdIn.isEmpty()) {
           randomizedQueue.enqueue(StdIn.readString());
       }

        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i= 0; i < number; i++) {
            if (iterator.hasNext()) {
                StdOut.println(iterator.next());
            }
        }
    }
}
