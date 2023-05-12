package com.example.project3;

public class Driver {
    public static void main(String[] args) {

        TawjihiDS test = new TawjihiDS();
        test.addStudent(300, "science", 55);
        test.addStudent(200,"Literature", 70);
        test.addStudent(120,"Literature", 70);
        test.addStudent(130,"science", 80);
        test.addStudent(115,"science", 80);
        test.removeStudent(300);


        test.printIdTree();






    }

}
