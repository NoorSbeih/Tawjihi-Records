package com.example.project3;

public class TawjihiDS {
    protected  DoublyLL students;
    protected AVL1 idTree;
    protected AVL2 gradeTree;

    TawjihiDS(){
        students = new DoublyLL();
        idTree = new AVL1();
        gradeTree = new AVL2();

    }

    public int idTreeHeight(){
        return idTree.getHeight();
    }
    public int gradeTreeHeight(){
        return gradeTree.getHeight();
    }

    public boolean addStudent(int id, String branch, double grade){

        if(idTree.searchElement(id) == null) {
            LLNode x = students.insert(new Student(id, branch, grade));
            idTree.insertElement(x);
            gradeTree.insertElement(x);
            return true;
        }
        else return false;
    }
    public boolean removeStudent(int id){

        if(idTree.searchElement(id) == null ){
            return false;
        }
        else {
            LLNode x = students.delete(id);
            idTree.deleteNode(id);
            gradeTree.delete(id, x.data.grade);
            return true;
        }

    }

    public boolean update(int id, String branch, double grade){
        AVLNode x =idTree.searchElement(id);
        if(x == null  )
            return false;
        else{
            gradeTree.delete(id,x.ptr.data.grade);
            x.ptr.data.setBranch(branch);
            x.ptr.data.setGrade(grade);
            gradeTree.insertElement(x.ptr);
            return true ;

        }


    }
    public String printList(){
        return students.toString();
    }
    public String printGradeTree(){
        return gradeTree.print();
    }
    public String printIdTree(){
        return idTree.print();
    }

    public String searchByGrade(double grade){
       AVL2Node x = gradeTree.searchElement(grade);
       if(x!=null)
             return x.ptr.toString();
       else return "None";
    }
    public LLNode searchByID(int id){
        AVLNode x = idTree.searchElement(id);
        if(x!=null)
          return x.ptr;
        else return null;

    }
    public int getTotalNum(){
        return idTree.getTotalNumberOfAVLNodes();
    }





}

