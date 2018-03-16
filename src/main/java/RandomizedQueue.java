import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] itemArray = (Item[]) new Object[10];

    private int size = 0;
    private int randIndex = 0;

    private boolean[] grid;

    public RandomizedQueue() {}

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        if (size == itemArray.length) {
            resizeArray(2 * itemArray.length);
        }

        itemArray[size++] = item;
        grid = new boolean[size];
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        randIndex = getRandIndex();
        Item dequeuedItem = itemArray[randIndex];

        if(randIndex != size-1) {
            itemArray[randIndex] = itemArray[size-1];
        }

        itemArray[--size] = null;

        if (size == itemArray.length / 4) {
            resizeArray(itemArray.length / 2);
        }

        return dequeuedItem;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        return itemArray[getRandIndex()];
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
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int randIndex = getRandIndex();
        private Item[] randomizedArray = itemArray;

        public boolean hasNext() {
            return itemArray[randIndex + 1] != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements left in array ... ");
            }

            for (int i = randomizedArray.length - 1; i > 0; i--) {
                if (randomizedArray[randIndex] != null) {
                    Item randomItem = randomizedArray[randIndex];

                    randomizedArray[randIndex] = randomizedArray[i];
                    randomizedArray[i] = randomItem;
                }
            }
            
            return itemArray[randIndex + 1];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Not supported in that case study");
        }
    }

    private int getRandIndex() {
        return StdRandom.uniform(size);
    }

    public static void main(String[] args) {
        RandomizedQueue randQueue = new RandomizedQueue();

        String one = "1";
        String two = "2";

        randQueue.enqueue(one);
        randQueue.enqueue(two);

        randQueue.enqueue("3");
        randQueue.enqueue("4");

             while (randQueue.iterator().hasNext()) {
                System.out.println(randQueue.iterator().next());
            }
    }
}
