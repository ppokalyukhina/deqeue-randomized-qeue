import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] itemArray = (Item[]) new Object[10];
    private int size = 0;

    /**
     * Initialize an empty constructor.
     */
    public RandomizedQueue() {}

    /**
     * Shows if queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Current size of the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Adds an Item to the end of the queue. If needed, resizes by 2.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        if (size == itemArray.length) {
            resizeArray(2 * itemArray.length);
        }

        itemArray[size++] = item;
    }

    /**
     * Gets a random item, removes and returns it.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int randIndex = getRandIndex();
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

    /**
     * Returns a random Item in the queue.
     */
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        return itemArray[getRandIndex()];
    }

    /**
     * Resize array by 2.
     */
    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < capacity; i++) {
            if (itemArray[i] != null) {
                copy[i] = itemArray[i];
            }
        }

        itemArray = copy;
    }

    /**
     * Iterates through the queue in a random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int arraySize = size;
        private Item[] copiedItemsArray;

        private int[] indexes = new int[size];

        private RandomizedIterator() {
            copiedItemsArray = (Item[]) new Object[arraySize];
            for (int i = 0; i < arraySize; i++) {
                copiedItemsArray[i] = itemArray[i];
            }
        }

        public boolean hasNext() {
            return arraySize > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements left in array ... ");
            }

            int randIndex = StdRandom.uniform(arraySize);
            Item randItem = copiedItemsArray[randIndex];

            if (randIndex != arraySize - 1) {
                copiedItemsArray[randIndex] = copiedItemsArray[arraySize - 1];
            }

            copiedItemsArray[--arraySize] = null;
            return randItem;
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

        Iterator iter = randQueue.iterator();

        for (int i= 0; i < randQueue.size; i++) {
            if (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
}
