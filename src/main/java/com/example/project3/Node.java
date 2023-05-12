package com.example.project3;

public class Node {
    public LLNode element;
    public Node next;

    Node(LLNode o){
        this(o,null);

    }
    Node (LLNode o, Node x){
        this.element = o;
        this.next = x;

    }

    public Object getElement() {
        return element;
    }

    public void setElement(LLNode element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
