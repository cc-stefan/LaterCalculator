package com.db.LaterCalculator.service;

import com.db.LaterCalculator.entity.Calculation;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CalculatorService {
    @Async
    public CompletableFuture<List<Calculation>> asyncDoCalculations(List<Calculation> calculations) {
        return CompletableFuture.supplyAsync(() -> {
            calculations.forEach(calculation -> {
                double res = doOperation(calculation);
                calculation.setResult(res);
            });
            return calculations;
        });
    }

    private double doOperation(Calculation calculation) {
        List<Double> operands = calculation.getOperands();
        String operation = calculation.getOperation();

        return switch (operation.toLowerCase()) {
            case "sum" -> operands.get(0) + operands.get(1);
            case "sub" -> operands.get(0) - operands.get(1);
            case "mul" -> operands.get(0) * operands.get(1);
            case "div" -> operands.get(0) / operands.get(1);
            default -> throw new IllegalArgumentException("Operation not supported: " + operation);
        };
    }
}