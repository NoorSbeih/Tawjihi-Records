package com.example.project3;

public class LLNode {
    protected Student data;
    protected LLNode next, prev;

    public LLNode()
    {
        next = null;
        prev = null;
        data = null;
    }

    public LLNode(Student data, LLNode next, LLNode prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public LLNode getNext() {
        return next;
    }

    public void setNext(LLNode next) {
        this.next = next;
    }

    public LLNode getPrev() {
        return prev;
    }

    public void setPrev(LLNode prev) {
        this.prev = prev;
    }
}
