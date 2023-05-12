package com.example.project3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {



        stage.getIcons().add(new Image("C:\\Users\\sbeih\\IntelliJ workspace\\project3\\src\\main\\resources\\com\\example\\project3\\icon.png"));
        stage.setTitle("Tawjihi records database");
        stage.setResizable(false);
        stage.setScene(main(stage));
        stage.show();
    }

    public Scene optionsScene(TawjihiDS ds, Stage stage , int check){

        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_LEFT);
        root.setId("pane");
        Scene scene = new Scene(root,1020,600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        Label idLabel = new Label("Student ID:");
        TextField idText = new TextField();
        idText.setTranslateX(33);
        HBox id = new HBox(idLabel,idText);


        Label gradeLabel = new Label("Student Grade");
        TextField gradeText = new TextField();
        gradeText.setTranslateX(10);
        HBox grade = new HBox(gradeLabel,gradeText);
        grade.setTranslateY(10);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setTranslateY(50);

        Label resultLabel = new Label();
        resultLabel.setTranslateY(50);
        resultLabel.setId("result");

        Label branchLabel = new Label("Student Branch");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Scientific",
                        "Literary"
                );
        ComboBox<String> branchBox = new ComboBox<>(options);
        if(check == 3){ //if only Scientific is chosen
            branchLabel.setDisable(true);
            branchBox.getSelectionModel().selectFirst();
            branchBox.setDisable(true);

        }
        else if(check==2){ //if only Literary is chosen
            branchLabel.setDisable(true);
            branchBox.getSelectionModel().selectLast();
            branchBox.setDisable(true);
        }
        branchBox.setTranslateX(10);
        HBox branch = new HBox(branchLabel,branchBox);
        branch.setTranslateY(15);

        Button prev = new Button("PREVIOUS");
        Button next = new Button("NEXT");
        HBox prevnext = new HBox(prev,next);
        prevnext.setDisable(true);
        prevnext.setSpacing(10);

        Button addbtn = new Button("ADD STUDENT");
        addbtn.setOnAction(e->{
            prevnext.setDisable(true);
            resultArea.setText("");
            try{
                if(Double.parseDouble(gradeText.getText()) <50 || Double.parseDouble(gradeText.getText()) >=100) //grade has to be between 50 and 100
                {
                    resultLabel.setId("error");
                    resultLabel.setText("Please enter a valid grade");

                }
                else {
                    if (ds.addStudent(Integer.parseInt(idText.getText()), branchBox.getValue(), Double.parseDouble(gradeText.getText()))) {
                        resultLabel.setText("Student added successfully");
                        resultLabel.setId("result");
                    }


                    else {
                        resultLabel.setText("Student already exists!");
                        resultLabel.setId("error");
                    }//if student exists before adding
                }
            }
            catch (Exception ex){
                resultLabel.setText("Please enter valid values");
                resultLabel.setId("error");


            }
        });
        Button deletebtn = new Button("DELETE STUDENT");
        deletebtn.setOnAction(e->{
            prevnext.setDisable(true);
            resultArea.setText("");
            try{
                if(ds.removeStudent(Integer.parseInt(idText.getText()))) {
                    resultLabel.setId("result");
                    resultLabel.setText("Student removed successfully!");
                }

                else{
                    resultLabel.setText("Student not found");
                    resultLabel.setId("error");
                }

            }
            catch (Exception exception){
                resultLabel.setId("error");
                resultLabel.setText("Please enter valid values");
            }
        });
        Button updatebtn = new Button("UPDATE STUDENT");
        updatebtn.setOnAction(e->{
            prevnext.setDisable(true);
            resultArea.setText("");
            try{
                if (ds.update(Integer.parseInt(idText.getText()), branchBox.getValue(), Double.parseDouble(gradeText.getText()))) {
                    resultLabel.setText("Student updated successfully");
                    resultLabel.setId("result");
                }
                else
                 {
                     resultLabel.setId("error");
                    resultLabel.setText("Student not found");

                }
            }
            catch (Exception exception){
                resultLabel.setId("error");
                resultLabel.setText("Please enter valid values");
            }
        });
        Button findbtn = new Button("FIND STUDENT");
        findbtn.setOnAction(e->{
            try{
                final LLNode[] x = {ds.searchByID(Integer.parseInt(idText.getText()))};
                if(x[0] !=null) {
                    resultLabel.setId("result");
                    resultArea.setText(x[0].data.toString());
                    resultLabel.setText("Student found!");
                    prevnext.setDisable(false);
                    final LLNode[] temp = {x[0]};
                    next.setOnAction(ev->{
                        temp[0] = temp[0].next;
                        resultArea.setText(temp[0].data.toString());
                    });
                    prev.setOnAction(ev->{
                        temp[0] = temp[0].prev;
                        resultArea.setText(temp[0].data.toString());

                    });
                }
                else {
                    resultLabel.setId("error");
                    resultLabel.setText("Student not found");
                    resultArea.setText("");
                    prevnext.setDisable(true);
                }
            }
            catch (Exception exception){
                resultLabel.setId("error");
                prevnext.setDisable(true);
                resultLabel.setText("Please enter a valid ID");
            }
        });
        Button printLL = new Button("PRINT FULL LINKED LIST");
        printLL.setOnAction(e->{
            prevnext.setDisable(true);
            resultLabel.setId("result");
            resultLabel.setText("Circular Doubly Linked List:");
            resultArea.setText(ds.printList());
        });
        Button printAvl1 = new Button("PRINT ID AVL");
        printAvl1.setOnAction(e->{
            resultLabel.setId("result");
            prevnext.setDisable(true);
            resultLabel.setText("ID AVL Tree:");
            resultArea.setText(ds.printIdTree());
        });
        Button printAvl2 = new Button("PRINT GRADE AVL");
        printAvl2.setOnAction(e->{
            resultLabel.setId("result");
            prevnext.setDisable(true);
            resultLabel.setText("Grade AVL Tree:");
            resultArea.setText(ds.printGradeTree());
        });
        Button printHeight = new Button("PRINT AVL HEIGHT");
        printHeight.setOnAction(e->{
            prevnext.setDisable(true);
            resultLabel.setText("");
            resultArea.setText("ID AVL Tree Height -> " + ds.idTreeHeight() +"\n" + "Grade AVL Tree Height -> " + ds.gradeTreeHeight());
        });
        Button back = new Button("BACK");
        back.setOnAction(e->{
            prevnext.setDisable(true);
            stage.setScene(main(stage));
        });
        Button searchByGrade = new Button("SEARCH BY GRADE");
        searchByGrade.setOnAction(e->{
            prevnext.setDisable(true);
            try {
                resultLabel.setText("Students with grade " + gradeText.getText() + " are:");
                resultArea.setText(ds.searchByGrade(Double.parseDouble(gradeText.getText())));
                resultLabel.setId("result");
            }
            catch (Exception exception){
                resultLabel.setId("error");
                resultLabel.setText("Please enter a valid grade");
                resultArea.setText("");
            }
        });
        Button totNum = new Button("GET NUMBER OF STUDENTS");
        totNum.setOnAction(e->{
            resultLabel.setText("Total Number of students in the database:");
            resultLabel.setId("result");
            resultArea.setText(String.valueOf(ds.getTotalNum()));
        });




        VBox buttons = new VBox(addbtn,deletebtn,updatebtn,findbtn,searchByGrade,totNum,printLL,printAvl1,printAvl2,printHeight,back,prevnext);
        buttons.setTranslateY(10);
        buttons.setSpacing(10);




        VBox box = new VBox(id,grade,branch,resultLabel,resultArea);
        box.setSpacing(10);
        box.setTranslateY(10);
        box.setTranslateX(5);

        HBox all = new HBox(box,buttons);
        all.setSpacing(50);






        root.add(all,0,0);
        return scene;

    }
    public Scene main(Stage stage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1020, 600);
        root.setId("pane");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());


        RadioButton science = new RadioButton("Science");
        RadioButton literary = new RadioButton("Literary");
        HBox branch = new HBox(science,literary);
        branch.setSpacing(190);

        Label intro = new Label("Pick one of the following branches (or both!)");
        Button fileLoader = new Button("Proceed");
        fileLoader.setTranslateX(140);
        VBox all = new VBox(intro,branch,fileLoader);
        fileLoader.setOnAction(e -> {
            try {
                if (literary.isSelected() && science.isSelected()) {
                    FileChooser x = new FileChooser();
                    x.setInitialDirectory(new File("src/main/resources"));
                    File selectedFile = x.showOpenDialog(stage);
                    try {
                        TawjihiDS ds = new TawjihiDS();
                        BufferedReader read1 = new BufferedReader(new FileReader(selectedFile));
                        String stud;
                        while ((stud = read1.readLine()) != null) {  //both science and literature in 1 list
                            String[] split = stud.split(",");
                            ds.addStudent(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]));


                        }
                        stage.setScene(optionsScene(ds, stage, 1));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (literary.isSelected() && !science.isSelected()) { //only literary is selected
                    FileChooser x = new FileChooser();
                    x.setInitialDirectory(new File("src/main/resources"));
                    File selectedFile = x.showOpenDialog(stage);
                    try {
                        TawjihiDS ds = new TawjihiDS();
                        BufferedReader read1 = new BufferedReader(new FileReader(selectedFile));
                        String stud;
                        while ((stud = read1.readLine()) != null) {
                            String[] split = stud.split(",");
                            if (split[1].replaceAll("\\s", "").equals("Literary"))
                                ds.addStudent(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]));


                        }
                        stage.setScene(optionsScene(ds, stage, 2));


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (!literary.isSelected() && science.isSelected()) //only science is selected
                {
                    FileChooser x = new FileChooser();
                    x.setInitialDirectory(new File("src/main/resources"));
                    File selectedFile = x.showOpenDialog(stage);
                    try {
                        TawjihiDS ds = new TawjihiDS();
                        BufferedReader read1 = new BufferedReader(new FileReader(selectedFile));
                        String stud;
                        while ((stud = read1.readLine()) != null) {
                            String[] split = stud.split(",");
                            if (split[1].replaceAll("\\s", "").equals("Scientific"))
                                ds.addStudent(Integer.parseInt(split[0]), split[1], Double.parseDouble(split[2]));


                        }
                        stage.setScene(optionsScene(ds, stage, 3));

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please pick at least one branch");
                    alert.showAndWait();

                }


            }catch (Exception exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Selected File Invalid");
                alert.setContentText("Please select a valid tawjihi file");
                alert.showAndWait();

            }
        }
            );
        root.add(all,0,0);
        all.setTranslateY(-100);

        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}