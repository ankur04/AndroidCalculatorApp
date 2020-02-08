package com.example.n01324338_ankurpravinpatel_cs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.n01324338_ankurpravinpatel_cs.model.Calculator;
import com.example.n01324338_ankurpravinpatel_cs.service.CalculatorService;

import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.ADDITION;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.DIVISION;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.MULTIPLICATION;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.NUM1;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.NUM2;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.SUBTRACTION;

public class MainActivity extends AppCompatActivity {

    Calculator calculator;
    TextView output;
    TextView history;
    String historyNum1 = "";
    boolean percent = false;
    CalculatorService calculatorService = new CalculatorService();
    String currentNumSelected = NUM1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);
        history = findViewById(R.id.history);
        output.setText("");
        calculator = new Calculator();

    }

    public void numButtonClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.button0:
                    output.append("0");
                    break;
                case R.id.button1:
                    output.append("1");
                    break;
                case R.id.button2:
                    output.append("2");
                    break;
                case R.id.button3:
                    output.append("3");
                    break;
                case R.id.button4:
                    output.append("4");
                    break;
                case R.id.button5:
                    output.append("5");
                    break;
                case R.id.button6:
                    output.append("6");
                    break;
                case R.id.button7:
                    output.append("7");
                    break;
                case R.id.button8:
                    output.append("8");
                    break;
                case R.id.button9:
                    output.append("9");
                    break;
                case R.id.button_:
                    if (!output.getText().toString().contains(".")) {
                        output.append(".");
                    }
                    break;
                case R.id.buttondiv:
                    performOperations();
                    calculator.setOperations(DIVISION);
                    break;
                case R.id.buttonMultiply:
                    performOperations();
                    calculator.setOperations(MULTIPLICATION);
                    break;
                case R.id.buttonMinus:
                    performOperations();
                    calculator.setOperations(SUBTRACTION);
                    break;
                case R.id.buttonplus:
                    performOperations();
                    calculator.setOperations(ADDITION);
                    break;
                case R.id.buttoneq:
                    performOperations();
                    setHistory();
                    break;
                case R.id.buttonpercent:
                    performPercentOperations();
                    break;

                default:
                    output.append("Error");
            }
        } catch (Exception e) {
            setException();
        }


    }

    private void setHistory() {
        if (!percent) {
            history.setText(historyNum1 + " " + calculatorService.convertToSymbol(calculator.getOperations()) + " " + calculator.getNum2() +
                    " = " + output.getText().toString());
        } else {
            percent = false;
            history.setText(historyNum1 + " " + calculatorService.convertToSymbol(calculator.getOperations()) + " "
                    + calculator.getNum2()*100/Double.valueOf(historyNum1) +
                    "% = " + output.getText().toString());
        }
    }


    private void performPercentOperations() {
        percent = true;
        calculator.setNum2((convertToDouble(output)*calculator.getNum1())/100);
        output.setText(String.valueOf(calculator.getNum2()));
    }

    private void performOperations() {
        if (currentNumSelected.equals(NUM1)) {
            calculator.setNum1(convertToDouble(output));
            currentNumSelected = NUM2;
            output.setText("");
        } else {
            calculator.setNum2(convertToDouble(output));
            currentNumSelected = NUM1;
            updateOutput(calculatorService.performArithmeticOperations(calculator));
        }
    }

    public void operationsButtonClicked(View view) {
        try {
            calculator.setNum1(convertToDouble(output));

            switch (view.getId()) {
                case R.id.buttonSquareRoot:
                    updateOutput(calculatorService.squareRoot(calculator));
                    history.setText("\u221A" + historyNum1 + " = " + output.getText().toString());
                    break;
                case R.id.buttonpluisminus:
                    updateOutput(calculatorService.plusMinus(calculator));
                    break;
                case R.id.buttonpercent:
                    updateOutput(calculatorService.percent(calculator));
                    break;
            }
        } catch (Exception e) {
            setException();
        }
    }


    public void updateOutput(double result) {
        output.setText(String.valueOf(result));
        historyNum1 = String.valueOf(calculator.getNum1());
        calculator.setNum1(result);
    }

    public double convertToDouble(TextView input) {
        return Double.valueOf(input.getText().toString());
    }

    public void operationsMemory(View view) {
        try {
            switch (view.getId()) {
                case R.id.buttonMPlus:
                    calculator.setMemory(calculator.getMemory() + convertToDouble(output));
                    output.setText("");
                    history.setText("Memory+ : " + calculator.getMemory());
                    break;
                case R.id.buttonMMinus:
                    calculator.setMemory(calculator.getMemory() - convertToDouble(output));
                    output.setText("");
                    history.setText("Memory- : " + calculator.getMemory());
                    break;
                case R.id.buttonMR:
                    output.setText(String.valueOf(calculator.getMemory()));
                    history.setText("Memory Result : " + calculator.getMemory());
                    break;
                case R.id.buttonMC:
                    calculator.setMemory(0);
                    output.setText("");
                    history.setText("Memory Cleared");
                    resetCalculator();
                    break;
            }
        } catch (Exception e) {
            setException();
        }
    }

    private void resetCalculator() {
        calculator.setNum2(0.0);
        calculator.setNum1(0.0);
        calculator.setOperations("");
        calculator.setResult(0.0);
    }


    private void setException() {
        history.setText("Error. Please enter again");
        output.setText("");
    }
}
