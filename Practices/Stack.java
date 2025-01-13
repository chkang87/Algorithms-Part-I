package Practices;

public class Stack<Item> {
    private Node first = null;

    private class Node{
        Item item;
        Node next;
    }

    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty(){
        return first.item == null;
    }

    public static void main(String[] args){
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        int a = s.pop();
        int b = s.pop();
        System.out.println(a);
        System.out.println(b);
    }
}
