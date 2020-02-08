package com.example.n01324338_ankurpravinpatel_cs.service;

import com.example.n01324338_ankurpravinpatel_cs.model.Calculator;

import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.ADDITION;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.DIVISION;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.MULTIPLICATION;
import static com.example.n01324338_ankurpravinpatel_cs.model.Constants.SUBTRACTION;

public class CalculatorService {

    public CalculatorService() {
    }

    public double squareRoot(Calculator calculator) {
        return Math.sqrt(calculator.getNum1());
    }

    public double plusMinus(Calculator calculator) {
        return -calculator.getNum1();
    }

    public double division(Calculator calculator) {
        return calculator.getNum1()/calculator.getNum2();
    }

    public double percent(Calculator calculator) {
        return 0.0;
    }

    public double multiply(Calculator calculator) {
        return calculator.getNum1()*calculator.getNum2();
    }


    public double performArithmeticOperations(Calculator calculator) {
        if (calculator.getOperations().equals(MULTIPLICATION)) {
            return multiply(calculator);
        } else if (calculator.getOperations().equals(DIVISION)) {
            return division(calculator);
        } else if (calculator.getOperations().equals(SUBTRACTION)) {
            return subtraction(calculator);
        } else if (calculator.getOperations().equals(ADDITION)) {
            return addition(calculator);
        }
        return 0.0;
    }

    public double addition(Calculator calculator) {
        return calculator.getNum1() + calculator.getNum2();
    }

    public double subtraction(Calculator calculator) {
        return calculator.getNum1() - calculator.getNum2();
    }

    public String convertToSymbol(String operations) {
        switch (operations) {
            case ADDITION:
                return "+";
            case SUBTRACTION:
                return "-";
            case MULTIPLICATION:
                return "\u00D7";
            case DIVISION:
                return "\u00F7";
            default:
                return " : ";
        }

    }
}
