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
        itemArray[randIndex] = null;

            for (int i = randIndex; i < itemArray.length - randIndex; i++) {
                if (itemArray[i+1] != null) {
                    itemArray[i] = itemArray[i+1];
                    itemArray[i+1] = null;
                } else {
                    break;
                }
            }

        size--;

        if (size > 0 && size == itemArray.length/4) {
            resizeArray(itemArray.length/2);
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
        Iterator<Item> iterator = new Iterator<Item>() {
            int randIndex = getRandIndex();
            int rand = randIndex + 1;

            public boolean hasNext() {
                return itemArray[rand] != null;
            }

            public Item next() {
                boolean alltrue = false;

                for (Boolean item : grid) {
                    if (!item) {
                        break;
                    } else {
                        alltrue = true;
                    }
                }

                if (alltrue) {
                    throw new java.util.NoSuchElementException("No more elements left in array ... ");
                }

                if (randIndex == 0 && grid[randIndex + 1]) {
                    rand = 0;
                }

                grid[rand] = true;
                return itemArray[rand];
            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException("Not supported in that case study");
            }
        };

        return iterator;
    }

    private int getRandIndex() {
        if (size > 1) {
            randIndex = StdRandom.uniform(0, size - 1);
        }

        return randIndex;
    }

    public static void main(String[] args) {
        RandomizedQueue randQueue = new RandomizedQueue();

        String one = "1";
        String two = "2";

        randQueue.enqueue(one);
        randQueue.enqueue(two);

        randQueue.enqueue("3");
        randQueue.enqueue("4");



        for (int i = 0; i < randQueue.size; i++) {
            System.out.println(randQueue.iterator().next() + " <<<< NEXT >>>");
        }
//            if (randQueue.iterator().hasNext()) {

//            }

        // System.out.println(randQueue.sample() + " >>>>> SAMPLE <<<");

//        randQueue.dequeue();
//        randQueue.dequeue();
    }
}
