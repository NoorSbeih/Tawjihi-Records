package com.example.project3;

public class DoublyLL {
    protected LLNode head;
    protected LLNode tail;
    public int size;
    public DoublyLL()
    {
        head = null;
        tail = null;
        size = 0;
    }
    public boolean isEmpty()
    {
        return head == null;
    }
    public int getSize()
    {
        return size;
    }

    public LLNode insert(Student data)
    {
        LLNode nptr = new LLNode(data, null, null);
        if (head == null)
        {
            nptr.setNext(nptr);
            nptr.setPrev(nptr);
            head = nptr;
            tail = head;
        }
        else
        {
            nptr.setPrev(tail);
            tail.setNext(nptr);
            head.setPrev(nptr);
            nptr.setNext(head);
            tail = nptr;
        }
        size++;
        return nptr;
    }

    public LLNode delete(int ID){

        LLNode current = head;
        if(size==0){
            return null;
        }
        else if (size>0 && head.data.ID == ID)
            removeFirst();

            
        else {
            while (current.next != head  ) {
                current = current.next;
                if(current.data.ID == ID) {
                    LLNode p = current.getPrev();
                    LLNode n = current.getNext();

                    p.setNext(n);
                    n.setPrev(p);
                    size-- ;
                    return current;
                }
            }



        }
        return current;


    }
    void removeFirst() {
        if(this.head != null) {


            if(this.head.next == this.head) {
                this.head = null;
            } else {


                LLNode temp = this.head;

                while (temp.next != this.head) {
                    temp = temp.next;
                }


                this.head = this.head.next;
                this.head.prev = temp;
                temp.next = this.head;
            }
            size--;
        }
    }
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        LLNode ptr;
        if (size == 0)
        {
            result.append("Empty");
            return result.toString();
        }
        if (head.getNext() == head)
        {
            result.append(head.data).append("\n");
            return result.toString();
        }
        result.append(head.getData()).append("\n");
        ptr = head.getNext();
        while (ptr.getNext() != head)
        {
            result.append(ptr.getData()).append("\n");
            ptr = ptr.getNext();
        }

        result.append(ptr.getData()).append("\n");
        return result.toString();
    }
}

