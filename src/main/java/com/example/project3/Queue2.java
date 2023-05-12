package com.example.project3;

public class Queue2 {
    int MAXSIZE = 1000;
    int currentSize = 0;
    AVL2Node[] items = new AVL2Node[MAXSIZE];
    int front, rear;

    Queue2() {
        front = -1;
        rear = -1;
    }

    // check if the queue is full
    boolean isFull() {
        if (front == 0 && rear == MAXSIZE - 1) {
            return true;
        }
        return false;
    }

    AVL2Node peek() {

        return items[front];

    }


    // check if the queue is empty
    boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    // insert elements to the queue
    void enQueue(AVL2Node element) {

        // if queue is full
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1) {
                // mark front denote first element of queue
                front = 0;
            }

            rear++;
            // insert element at the rear
            items[rear] = element;
            currentSize++;
        }

    }


    AVL2Node deQueue() {
        AVL2Node element;

        // if queue is empty
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            // remove element from the front of queue
            element = items[front];

            // if the queue has only one element
            if (front >= rear) {
                front = -1;
                rear = -1;
            } else {
                // mark next element as the front
                front++;
            }
            currentSize--;
            return (element);

        }
    }




    int size() {
        return currentSize;
    }
}
