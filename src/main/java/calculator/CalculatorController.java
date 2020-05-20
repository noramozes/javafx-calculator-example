package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";
    private boolean floatNum = false;

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           if(floatNum) {
               display.setText(String.format("%s", result));
           } else {
               display.setText(String.format("%.0f", result));
           }
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    public void allClearAction() {
        display.setText("0");
        startNumber = true;
        operator = "";
        floatNum = false;
        System.out.println("All Clear");
    }

    public void dotAction() {
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
            System.out.println(".");
            floatNum = true;
        }
    }

    public void plusMinusAction() {
        if(!display.getText().startsWith("-")) {
            display.setText("-" + display.getText());
            System.out.println("Sign changed to minus");
        } else {
            display.setText(display.getText().replace("-", ""));
            System.out.println("Sign changed to plus");
        }
    }
}
