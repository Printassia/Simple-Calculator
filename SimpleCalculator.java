package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {
    private TextField inputOne = new TextField();
    private TextField inputTwo = new TextField();
    private Button btnAdd = new Button("+");
    private Button btnSubtract = new Button("-");
    private Button btnDivide = new Button("/");
    private Button btnMultiply = new Button("x");
    private Button btnCalculate = new Button("Calculate");
    private TextField displayResults = new TextField();
    private Button btnClear = new Button("C");
    private Label firstNumberLabel = new Label("First Number");
    private Label secondNumberLabel = new Label("Second Number");
    private Label resultsLabel = new Label("Results");

    @Override
    public void start(Stage primaryStage) {
        //GridPane properties
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //First Number
        gridPane.add(firstNumberLabel, 0,0);
        gridPane.add(inputOne,1,0);

        //HBox Operators
        gridPane.add(operatorHBox(),1,1);

        //Second Number
        gridPane.add(secondNumberLabel,0,2);
        gridPane.add(inputTwo, 1,2);

        //Calculate & Clear Button
        gridPane.add(calculateAndClearHBox(),1,3);

        //Results
        gridPane.add(resultsLabel,0,4);
        gridPane.add(displayResults,1,4);
        displayResults.setEditable(false);

        //Run action code
        actionCode();

        //Scene
        primaryStage.setScene(new Scene(gridPane,300,280));
        primaryStage.setTitle("Simple Calculator");
        primaryStage.show();
    }

    /** HBOX UI: Operators*/
    private HBox operatorHBox(){
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btnAdd,btnSubtract,btnMultiply,btnDivide);
        return hbox;
    }

    /** HBOX UI: Calculate & Clear Buttons*/
    private HBox calculateAndClearHBox(){
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btnCalculate, btnClear);
        return hbox;
    }

    /** Calculate the first and second numbers based on the choosen operator
     *  Clear all the fields with the clear button */
    public void calculate(ActionEvent e) {
        int num1, num2, answer = 0;
        char symbol;

        //Read numbers from the TextFields
        num1 = Integer.parseInt(inputOne.getText());
        num2 = Integer.parseInt(inputTwo.getText());

        //Clear all TextFields
        if (e.getSource() == btnClear){
            inputOne.clear();
            inputTwo.clear();
            displayResults.clear();
            return;
        }

        //Perform calculations based on operators
        if (e.getSource() == btnAdd) {
            answer = num1 + num2;
        } else if (e.getSource() == btnSubtract) {
            answer = num1 - num2;
        } else if (e.getSource() == btnMultiply) {
            answer = num1 * num2;
        } else if (e.getSource() == btnDivide){
            answer = num1 / num2;
        }

        //Display Results
        int calculation = answer;
        btnCalculate.setOnAction(actionEvent -> {
            displayResults.setText("" + calculation);
        });
    }

    /** Button Actions */
    public void actionCode() {
        //Each button will set action to run the calculate method
        btnAdd.setOnAction(e -> calculate(e));
        btnSubtract.setOnAction(e -> calculate(e));
        btnMultiply.setOnAction(e -> calculate(e));
        btnDivide.setOnAction(e -> calculate(e));
        btnClear.setOnAction(e -> calculate(e));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
