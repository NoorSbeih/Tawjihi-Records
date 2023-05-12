package com.example.project3;

public class LinkedList {
    private int size;
    private Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

  
    public void insert(LLNode element) {
        Node node = new Node(element);
        node.element = element;
        Node current = this.head;

        if (this.head == null) {
            this.head = node;
            this.head.next = null;
            this.size = 1;

        } else {

            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
            node.next = null;
            this.size += 1;
        }
    }







    public void deleteFirst() {
        if(head != null){
            this.head = this.head.next;
            this.size--;
        }
    }


    public boolean remove(int index){
        if(index>size-1) return false;
        else if (index ==0) deleteFirst();
        else if(index>0 && index<=size-1){
            Node current = head;
            for (int i = 0; i<=index-2;i++)
                current= current.next;
            current.next=current.next.next;
            size--;
            return true;

        }
        else return false;
    return true;
    }





    public boolean removeSeat(int num){  //method to remove seat based on the seat number
        if (size == 0) {
            return false;
        } else {
            Node current = head;
            for (int i = 0; i < size; i++) {
                if (current.element.data.ID == num) {
                    remove(i);
                }
                current = current.next;
            }
        }
        return false;
    }


    public String toString(){
        Node current = head;
        StringBuilder output = new StringBuilder();
        for (int i=0; i<=size-1;i++){
            output.append("[").append(current.element.data.ID).append("]");
            current=current.next;

        }
        return output.toString();
    }


    public int getListSize(){
        return size;
    }

}