import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private Node first;
    private Node last;
    private Node current;

    private int size = 0;

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

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
            last = null;
            Node previous = null;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("The item should not be null.");
        }

        if (last == null && !isEmpty()) {
            last = new Node();
            last.item = item;

            first.next = last;
            last.previous = first;
        } else if (isEmpty() && last != null) {
            last = new Node();
            last.item = item;

            first = last;
        } else {
            Node oldlast = last;

            Node lastAdded = new Node();
            lastAdded.item = item;

            oldlast.next = lastAdded;

            last = lastAdded;
            last.previous = oldlast;
        }

        size++;
    }
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No such element");
        }

        Item item = first.item;
        first = first.next;

        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No such element");
        }

        Node newLast = last.previous;
        last = newLast;

        newLast.next = null;

        size--;
        return newLast.item;
    }

    public Iterator<Item> iterator() {
        Iterator<Item> iterator = new Iterator<Item>() {
            public boolean hasNext() {
                if (current == null) {
                    current = first;

                    return true;
                } else if (current.next != null) {
                    Node oldCurrent = current;
                    current = oldCurrent.next;

                    return true;
                } else {
                    last = current;
                    return false;
                }
            }

            public Item next() {
                if (current == null) {
                    throw new java.util.NoSuchElementException("The next element is null...");
                }

                return current.item;
            }

            public void remove() {
                throw new java.lang.UnsupportedOperationException("Not supported in that case study");
            }
        };

        return iterator;
    }

    public static void main(String[] args) {
        String zero = "0";
        String one = "1";
        String two = "2";
        String three = "3";
        String four = "4";

        Deque deque = new Deque();

        deque.addFirst(zero);
        deque.addFirst(one);
        deque.addFirst(two);
        deque.removeFirst();

        while (deque.iterator().hasNext()) {
            System.out.println(deque.iterator().next() + " Object");
        }
    }
}
