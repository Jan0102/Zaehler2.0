package ZahlerFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI1 {


    //Class variables
    private GridPane layout;
    private Stage stage1;
    private Button button;
    private ComboBox<CountingObject> box;
    private TextField startField;
    private Label errorLabel;
    private Label objectLabel;
    private Label startLabel;



    public GUI1(Database database) {
        stageTitle();
        layout();
        makeObjectBox();
        makeObjectLabel();
        makeStartField();
        makeStartLabel();
        makeButton();
        makeErrorLabel();
        setChildren();
        setScene();
    }



    private void stageTitle() {
        stage1 = new Stage();
        stage1.setTitle("First Window");
    }



    private void layout() {
        layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(10);
        layout.setHgap(8);
    }



    private void makeObjectBox() {
        box = new ComboBox<>();
        box.getItems().addAll(CountingObject.values());
        GridPane.setConstraints(box,1,0);
    }



    private void makeObjectLabel() {
        objectLabel = new Label("Choose an object:");
        GridPane.setConstraints(objectLabel,0,0);
    }



    //Doesnt work yet
    private void makeStartField() {
        Counter counter = new Counter(box.getValue());
        startField = new TextField(String.valueOf(counter.number));
        GridPane.setConstraints(startField,1,1);
    }



    private void makeStartLabel() {
        startLabel = new Label("Choose a starting value:");
        GridPane.setConstraints(startLabel,0,1);

    }



    private void makeButton() {
        button = new Button("Confirm");
        button.setOnAction(e -> {
            Counter counter = new Counter(box.getValue());
            Database database = new Database();
            if(box.getValue() != null) {
                new GUI2(counter, database);
                stage1.close();
            } else {
                errorLabel.setVisible(true);
            }
        });
        GridPane.setConstraints(button,0,6);
    }



    private void makeErrorLabel() {
        errorLabel = new Label("Choose an object!");
        errorLabel.setVisible(false);
        errorLabel.getStyleClass().add("label-red");
        GridPane.setConstraints(errorLabel,0,5);

    }



    private void setChildren() {
        layout.getChildren().addAll(box,objectLabel,startField,startLabel,button,errorLabel);

    }



    private void setScene() {
        Scene scene1 = new Scene(layout, 350, 300);
        scene1.getStylesheets().add("ZahlerFX/Optik.css");
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.show();
    }



}
