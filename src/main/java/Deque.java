import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
//    private Node current;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private int size = 0;

    /**
     * Initialize an empty constructor.
     */
    public Deque() {
    }

    /**
     * Shows if queue is empty.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Current size of the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Adds item to the beginning of the queue.
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("The item should not be null");
        }

        if (!isEmpty()) {
            Node oldfirst = first;
            Node firstAdded = new Node();
            firstAdded.item = item;
            firstAdded.next = oldfirst;

            first = firstAdded;

            oldfirst.previous = first;

            if (last == null) {
                last = oldfirst;
            }
        } else {
            Node firstAdded = new Node();
            firstAdded.item = item;

            first = firstAdded;
            last = first;
        }

        size++;
    }

    /**
     * Adds an item to the end of the queue.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("The item should not be null.");
        }

        if (isEmpty()) {
            addFirst(item);
        }
        else {
            Node oldlast = last;

            Node lastAdded = new Node();
            lastAdded.item = item;

            oldlast.next = lastAdded;

            last = lastAdded;
            last.previous = oldlast;
            size++;
        }
    }

    /**
     * Removes first item in the queue and returns it.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No such element");
        }

        Item item = first.item;
        first = first.next;

        size--;
        return item;
    }

    /**
     * Removes last Item in the queue and returns it.
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No such element");
        }

        Item oldLast;

        if (size == 1) {
            oldLast = removeFirst();
        } else {
            oldLast = last.item;
            Node newLast = last.previous;
            last = newLast;

            newLast.next = null;
            size--;
        }
        return oldLast;
    }

    /**
     * Iterates over queue and returns next item in the queue.
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        private DequeIterator() {
            if (current == null) {
                this.current = first;
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("The next element is null...");
            }

            Node oldCurrent = current;
            current = oldCurrent.next;

            return oldCurrent.item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Not supported in that case study");
        }
    }

    public static void main(String[] args) {
        // left empty on a purpose.

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);

        for (int i = 0; i < 5; i++) {
            while (deque.iterator().hasNext()) {
                System.out.println(deque.iterator().next());
            }
        }
    }
}
