package com.example.project3;

public class AVL2Node {

    int h;
    double grade;
    LinkedList ptr;
    AVL2Node left;
    AVL2Node right;


    public AVL2Node(LLNode x) {

        left = null;
        right = null;
        ptr = new LinkedList();
        ptr.insert(x);
        this.grade = x.data.grade;
        h = 0;
    }
}

