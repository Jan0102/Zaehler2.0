package ZahlerFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUI2 {



    //Class variables
    private Stage stage2;
    private Spinner<Integer> step;
    private GridPane grid;
    private Button backButton;
    private Button resetButton;
    private Button plus;
    private Button minus;
    private Label name;
    private Label zahl;



    public GUI2(Counter counter){
        stageTitle();
        layout();
        makeBackButton();
        makeResetButton(counter);
        makePlusButton(counter);
        makeMinusButton(counter);
        makeNameLabel(counter);
        makeCounterLabel(counter);
        makeStepCount(counter);
        setChildren();
        setScene();
    }



    private void stageTitle() {
        stage2 = new Stage();
        stage2.setTitle("Second Window");
    }



    private void layout() {
        //Layout GridPane
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(8);
    }



    private void makeBackButton() {
        backButton = new Button("Back");
        backButton.setPrefSize(70,40);
        backButton.setOnAction(e -> {
            new GUI1();
            stage2.close();
        });
        GridPane.setConstraints(backButton,0,0);
    }



    private void makeResetButton(Counter counter) {
        resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            counter.reset();
            zahl.setText(String.valueOf(counter.number));
        });
        GridPane.setConstraints(resetButton,8,3);
    }



    private void makePlusButton(Counter counter) {
        plus = new Button("+\n");
        plus.setPrefSize(15,15);
        plus.setOnAction(e -> {
            counter.countUp();
            zahl.setText(String.valueOf(counter.number));
        });
        GridPane.setConstraints(plus,5,13);
    }



    private void makeMinusButton(Counter counter) {
        minus = new Button("-\n");
        minus.setPrefSize(15,15);
        minus.setOnAction(e -> {
            counter.countDown();
            zahl.setText(String.valueOf(counter.number));
        });
        GridPane.setConstraints(minus,4,13);
    }



    private void makeNameLabel(Counter counter) {
        name = new Label(String.valueOf(counter.name) + " :");
        name.setPrefWidth(150);
        name.getStyleClass().add("label-bold");
        GridPane.setConstraints(name,0,11);
    }



    private void makeCounterLabel(Counter counter) {
        zahl = new Label(String.valueOf(counter.number));
        zahl.setPrefWidth(200);
        GridPane.setConstraints(zahl,5,11);
    }



    private void makeStepCount(Counter counter) {
        step = new Spinner<Integer>(1,1000000,counter.stepCount);
        step.setEditable(true);
        step.valueProperty().addListener((v, oldValue, newValue) -> counter.setStepCount(newValue));
        GridPane.setConstraints(step,8,0);
    }



    private void setChildren() {
        grid.getChildren().addAll(backButton,name,resetButton,plus,minus,zahl,step);
    }



    private void setScene() {
        Scene scene2 = new Scene(grid,550,300);
        scene2.getStylesheets().add("ZahlerFX/Optik.css");
        stage2.setScene(scene2);
        stage2.setResizable(false);
        stage2.show();
    }



}
