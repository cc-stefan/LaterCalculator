package com.db.LaterCalculator.entity;

import java.util.List;

public class Calculation {
    private List<Double> operands;
    private String operation;
    private Double result;

    public Calculation() {
    }

    public Calculation(List<Double> operands, String operation) {
        this.operands = operands;
        this.operation = operation;
    }

    public Calculation(List<Double> operands, String operation, Double result) {
        this.operands = operands;
        this.operation = operation;
        this.result = result;
    }

    public List<Double> getOperands() {
        return operands;
    }

    public void setOperands(List<Double> operands) {
        this.operands = operands;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String extractOperationSymbol() {
        return switch (this.operation) {
            case "sum" -> "+";
            case "sub" -> "-";
            case "mul" -> "*";
            case "div" -> "/";
            default -> null;
        };
    }
}
