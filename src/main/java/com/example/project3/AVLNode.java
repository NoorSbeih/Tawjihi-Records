package com.example.project3;

public class AVLNode {

        int h;

        int ID;
        LLNode ptr;
        AVLNode left;
        AVLNode right;


        public AVLNode(LLNode ptr) {

            left = null;
            right = null;
            this.ptr=ptr;
            this.ID = ptr.data.ID;
            h = 0;
        }
    }

