package Practices;

public class Deque<Item> implements Iterable<Item> {

    Node first, last;

    public class Node{
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque(){
        Deque<Item> a = new Deque<Item>();
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null && last == null;
    }

    // return the number of items on the deque
    public int size(){
        return 0;
    }

    // add the item to the front
    public void addFirst(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    // add the item to the back
    public void addLast(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        Item item = first.item;
        first = first.next;
    }

    // remove and return the item from the back
    public Item removeLast(){
        Item item = last.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }
        
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){

    }

}