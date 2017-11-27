import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] itemArray = (Item[]) new Object[10];
    private int N = 0;

    private int size = 0;
    private int index = 0;

    public RandomizedQueue() {}

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public Item[] getarray() {
        return this.itemArray;
    }

    public void enqueue(Item item) {
        if (size == itemArray.length) {
            resizeArray(2 * itemArray.length);
        }

        itemArray[size++] = item;
    }

    public Item dequeue() {
        int t = 0;
        if (size > 1) {
            t = StdRandom.uniform(0, size - 1);

            System.out.println(t + " random Index");
        } else if (size == 0) {
            t = 0;
        } else if (size == 1) {
            t = 1;
        }

        Item toRemove = itemArray[t];
        itemArray[t] =  null;

        size--;

        if (size > 0 && size == itemArray.length/4){
            resizeArray(itemArray.length/2);
        }
        return toRemove;
    }

    public Item sample() {
        return null;
    }

    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            if (itemArray[i] != null) {
                copy[i] = itemArray[i];
            }
        }

        itemArray = copy;
    }

    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {
        RandomizedQueue randQueue = new RandomizedQueue();

        String one = "1";
        String two = "2";
        randQueue.enqueue(one);
        randQueue.enqueue(two);

        randQueue.enqueue(one);
        randQueue.enqueue(two);

        randQueue.enqueue(one);
        randQueue.enqueue(two);

        randQueue.enqueue(two);

        randQueue.dequeue();
        randQueue.dequeue();
        randQueue.dequeue();

        for (Object o : randQueue.getarray()) {
            System.out.println(o);
        }
    }
}
