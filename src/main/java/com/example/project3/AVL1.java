package com.example.project3;



class AVL1
{
    private AVLNode root;


    public AVL1()
    {
        root = null;
    }

   

    public void removeAll()
    {
        root = null;
    }
    public boolean checkEmpty()
    {
        return root == null;
    }
    int getHeight(){
        return root.h;
    }



    private int getHeight(AVLNode node )
    {
        return node == null ? -1 : node.h;
    }

    //create maxAVLNode() method to get the maximum height from left and right node  
    private int getMaxHeight(int leftAVLNodeHeight, int rightAVLNodeHeight)
    {
        return Math.max(leftAVLNodeHeight, rightAVLNodeHeight);
    }


    //method to insert ID in the AVL Tree
    public void insertElement(LLNode x)
    {
        root = insertElement(x, root);
    }

    private AVLNode insertElement(LLNode element, AVLNode node)
    {
        //check whether the node is null or not  
        if (node == null)
            node = new AVLNode(element);
        else if (element.data.ID < node.ID) //if node is less than current, go left
        {
            node.left = insertElement( element, node.left );
        }
        else if( element.data.ID > node.ID ) //if node is more, go right
        {
            node.right = insertElement( element, node.right );
        }

        updateHeight(node);

        return reBalance(node);

    }

    private AVLNode rotateWithLeftChild(AVLNode node2)
    {
        AVLNode node1 = node2.left;
        node2.left = node1.right;
        node1.right = node2;
        node2.h = getMaxHeight( getHeight( node2.left ), getHeight( node2.right ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.left ), node2.h ) + 1;
        return node1;
    }

    private AVLNode rotateWithRightChild(AVLNode node1)
    {
        AVLNode node2 = node1.right;
        node1.right = node2.left;
        node2.left = node1;
        node1.h = getMaxHeight( getHeight( node1.left ), getHeight( node1.right ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.right ), node1.h ) + 1;
        return node2;
    }


    public int getTotalNumberOfAVLNodes()
    {
        return getTotalNumberOfAVLNodes(root);
    }
    private int getTotalNumberOfAVLNodes(AVLNode head)
    {
        if (head == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getTotalNumberOfAVLNodes(head.left);
            length = length + getTotalNumberOfAVLNodes(head.right);
            return length;
        }
    }

    public AVLNode searchElement(int ID)
    {
        return searchElement(root, ID);
    }

    private AVLNode searchElement(AVLNode head, int ID)
    {
        boolean check = false;
        while ((head != null))
        {
            int  headElement = head.ID    ;
            if (ID < headElement)
                head = head.left;
            else if (ID > headElement)
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

    public void inorderTraversal()
    {
        inorderTraversal(root);
    }
    private void inorderTraversal(AVLNode head)
    {
        if (head != null)
        {
            inorderTraversal(head.left);
            System.out.print(head.ID+" ");
            inorderTraversal(head.right);
        }
    }

    public void preorderTraversal()
    {
        preorderTraversal(root);
    }
    private void preorderTraversal(AVLNode head)
    {
        if (head != null)
        {
            System.out.print(head.ID+" ");
            preorderTraversal(head.left);
            preorderTraversal(head.right);
        }
    }

    public void postorderTraversal()
    {
        postorderTraversal(root);
    }

    private void postorderTraversal(AVLNode head)
    {
        if (head != null)
        {
            postorderTraversal(head.left);
            postorderTraversal(head.right);
            System.out.print(head.ID+" ");
        }
    }

    private AVLNode reBalance(AVLNode node) {
        int balanceFactor = balanceFactor(node);

        // if left heavy
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

        // if right heavy
        if (balanceFactor > 1) {
            if (balanceFactor(node.right) >= 0) {
                // Rotate left
                node = rotateWithRightChild(node);
            } else {
                // Rotate right-left
                node.right = rotateWithLeftChild(node.right);
                node = rotateWithRightChild(node);
            }
        }

        return node;
    }
    public void deleteNode(int key) {
        root = deleteNode(key, root);
    }

    private AVLNode deleteNode(int key, AVLNode node) {
        if (node == null) {
            return null;
        }

        // Traverse the tree to the left or right depending on the key
        if (key < node.ID) {
            node.left = deleteNode(key, node.left);
        } else if (key > node.ID) {
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
        return reBalance(node);

    }

    private void updateHeight(AVLNode node) {
        int leftChildHeight = getHeight(node.left);
        int rightChildHeight = getHeight(node.right);
        node.h = Math.max(leftChildHeight, rightChildHeight) + 1;
    }
    private void deleteNodeWithTwoChildren(AVLNode node) {
        AVLNode maxFromLeft = findMaximum(node.left);


        node.ptr = maxFromLeft.ptr ;
        node.ID = maxFromLeft.ID;


        node.left = deleteNode(maxFromLeft.ID, node.left);
    }
    private AVLNode findMaximum(AVLNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    private int balanceFactor(AVLNode node) {

        return getHeight(node.right) - getHeight(node.left);

    }
    public String print(){
       return  levelTraversal(root);
    }
    private String levelTraversal(AVLNode root)
    {
        StringBuilder result = new StringBuilder();
        if (root == null)
            return "Empty";

        Queue q = new Queue();

        q.enQueue(root);

        while (true) {


            int nodeCount = q.size();
            if (nodeCount == 0)
                break;


            while (nodeCount > 0) {
                AVLNode node = q.peek();
                result.append(node.ID).append(" ");
                q.deQueue();
                if (node.left != null)
                    q.enQueue(node.left);
                if (node.right != null)
                    q.enQueue(node.right);
                nodeCount--;
            }
           result.append("\n");

        }
        return result.toString();
    }
}  
