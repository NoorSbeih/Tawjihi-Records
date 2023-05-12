package com.example.project3;

public class AVL2 {
    private AVL2Node root;


    public AVL2()
    {
        root = null;
    }


    public void removeAll()
    {
        root = null;
    }


    public boolean checkEmpty()
    {
        if(root == null)
            return true;
        else
            return false;
    }
    int getHeight(){
        return root.h;
    }

    private int getHeight(AVL2Node node )
    {
        return node == null ? -1 : node.h;
    }

    private int getMaxHeight(int leftAVL2NodeHeight, int rightAVL2NodeHeight)
    {
        return Math.max(leftAVL2NodeHeight, rightAVL2NodeHeight);
    }
    public void insertElement(LLNode x)
    {
        root = insertElement(x, root);
    }

    //method to insert grade in the AVL Tree recursively
    private AVL2Node insertElement(LLNode key, AVL2Node node) {
        // No node at current position --> store new node at current position
        if (node == null) {
            node = new AVL2Node(key);
        }


        else if (key.data.grade< node.grade) { //go left if key is less than current node
            node.left = insertElement(key, node.left);
        } else if (key.data.grade > node.grade) {
            node.right = insertElement(key, node.right); // go right if key is more than current node
        } else { //if Key is found add to the linked list
            node.ptr.insert(key);
        }
        updateHeight(node);

        return rebalance(node);


    }






    // method to perform rotation of binary tree node with left child
    private AVL2Node rotateWithLeftChild(AVL2Node node2)
    {
        AVL2Node node1 = node2.left;
        node2.left = node1.right;
        node1.right = node2;
        node2.h = getMaxHeight( getHeight( node2.left ), getHeight( node2.right ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.left ), node2.h ) + 1;
        return node1;
    }

    //method to perform rotation of binary tree node with right child
    private AVL2Node rotateWithRightChild(AVL2Node node1)
    {
        AVL2Node node2 = node1.right;
        node1.right = node2.left;
        node2.left = node1;
        node1.h = getMaxHeight( getHeight( node1.left ), getHeight( node1.right ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.right ), node1.h ) + 1;
        return node2;
    }



    //method to get total number of nodes in the AVL Tree
    public int getTotalNumberOfAVL2Nodes()
    {
        return getTotalNumberOfAVL2Nodes(root);
    }
    private int getTotalNumberOfAVL2Nodes(AVL2Node head)
    {
        if (head == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getTotalNumberOfAVL2Nodes(head.left);
            length = length + getTotalNumberOfAVL2Nodes(head.right);
            return length;
        }
    }


    public AVL2Node searchElement(double grade)
    {
        return searchElement(root, grade);
    }

    private AVL2Node searchElement(AVL2Node head, double grade)
    {
        boolean check = false;
        while ((head != null))
        {
            double headElement = head.grade    ;
            if (grade < headElement)
                head = head.left;
            else if (grade > headElement)
                head = head.right;
            else
            {
                check = true;
                break;
            }

        }
        if(check)
          return head;
        else return null;

    }
    public boolean delete(int id, double grade ){
        AVL2Node x = searchElement(grade);
        if(x == null) {
            return false;
        }
        else{
            x.ptr.removeSeat(id);
            if(x.ptr.getListSize()==0) {
                deleteNode(grade);
            }
            return true;
        }
        

    }

    public void inorderTraversal()
    {
        inorderTraversal(root);
    }
    private void inorderTraversal(AVL2Node head)
    {
        if (head != null)
        {
            inorderTraversal(head.left);
            System.out.print(head.grade+" ");
            inorderTraversal(head.right);
        }
    }

    public void preorderTraversal()
    {
        preorderTraversal(root);
    }
    private void preorderTraversal(AVL2Node head)
    {
        if (head != null)
        {
            System.out.print(head.grade+" ");
            preorderTraversal(head.left);
            preorderTraversal(head.right);
        }
    }

    public void postorderTraversal()
    {
        postorderTraversal(root);
    }

    private void postorderTraversal(AVL2Node head)
    {
        if (head != null)
        {
            postorderTraversal(head.left);
            postorderTraversal(head.right);
            System.out.print(head.grade+" ");
        }
    }
    public String print(){
        return levelTraversal(root);
    }
    private String levelTraversal(AVL2Node root)
    {
        StringBuilder result = new StringBuilder();
        if (root == null)
            return "Empty";

        Queue2 q = new Queue2();

        q.enQueue(root);

        while (true) {


            int nodeCount = q.size();
            if (nodeCount == 0)
                break;

            while (nodeCount > 0) {
                AVL2Node node = q.peek();
                result.append(node.grade).append("->").append(node.ptr).append(" ");
                q.deQueue();
                if (node.left != null)
                    q.enQueue(node.left);
                if (node.right != null)
                    q.enQueue(node.right);
                nodeCount--;
            }
            result.append("\n"  );

        }
        return result.toString();
    }
    private AVL2Node rebalance(AVL2Node node) {
        int balanceFactor = balanceFactor(node);

        // Left heavy case
        if (balanceFactor < -1) {
            if (balanceFactor(node.left) <= 0) {
                // Rotate right
                node = rotateWithLeftChild(node);
            } else {
                // Rotate left-right
                node.left = rotateWithRightChild(node.left);
                node = rotateWithLeftChild(node);
            }
        }

        // Right heavy case
        if (balanceFactor > 1) {
            if (balanceFactor(node.right) >= 0) {    // Case 3
                // Rotate left
                node = rotateWithRightChild(node);
            } else {                                 // Case 4
                // Rotate right-left
                node.right = rotateWithLeftChild(node.right);
                node = rotateWithRightChild(node);
            }
        }

        return node;
    }
    public void deleteNode(double key) {
        root = deleteNode(key, root);
    }

    AVL2Node deleteNode(double key, AVL2Node node) {
        if (node == null) {
            return null;
        }

        if (key < node.grade) {
            node.left = deleteNode(key, node.left);
        } else if (key > node.grade) {
            node.right = deleteNode(key, node.right);
        }

        // At this point, "node" is the node to be deleted

        // Node has no children --> just delete it
        else if (node.left == null && node.right == null) {
            node = null;
        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildren(node);
        }
        if (node == null) {
            return null;
        }

        updateHeight(node);

        return rebalance(node);

    }

    private void updateHeight(AVL2Node node) {
        int leftChildHeight = getHeight(node.left);
        int rightChildHeight = getHeight(node.right);
        node.h = Math.max(leftChildHeight, rightChildHeight) + 1;
    }
    private void deleteNodeWithTwoChildren(AVL2Node node) {
        AVL2Node maxFromLeft = findMinimum(node.left);

        node.grade = maxFromLeft.grade;
        node.ptr = maxFromLeft.ptr;

        node.left = deleteNode(maxFromLeft.grade, node.left);
    }
    private AVL2Node findMinimum(AVL2Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    private int balanceFactor(AVL2Node node) {

        return getHeight(node.right) - getHeight(node.left);

    }
}  


