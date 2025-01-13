package Practices;

public class Queue<Item> {

    Node first, last;

    private class Node{
        Item item;
        Node next;
    }

    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public static void main(String[] args){
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        int a = q.dequeue();
        int b = q.dequeue();
        System.out.println(a);
        System.out.println(b);
    }
}
