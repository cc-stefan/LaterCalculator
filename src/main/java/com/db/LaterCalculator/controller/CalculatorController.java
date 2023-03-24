package com.db.LaterCalculator.controller;

import com.db.LaterCalculator.entity.Calculation;
import com.db.LaterCalculator.service.CalculatorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/do-math")
    public String doMath(@RequestBody List<Calculation> calculations) {
        String fileName = "results.json";
        CompletableFuture<List<Calculation>> futureResult = calculatorService.asyncDoCalculations(calculations);
        futureResult.thenAccept(result -> {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(new File(fileName), result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return fileName;
    }

    @GetMapping("/check-finished/{filename}")
    public ResponseEntity<List<Calculation>> checkFinished(@PathVariable String filename) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Calculation> calculations = objectMapper.readValue(
                    new File(filename),
                    new TypeReference<List<Calculation>>() {
                    }
            );
            return ResponseEntity.ok(calculations);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/results/{filename}")
    public ModelAndView getResults(@PathVariable String filename) {
        List<Calculation> calculations;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            calculations = objectMapper.readValue(new File(filename), new TypeReference<List<Calculation>>() {
            });
        } catch (IOException e) {
            calculations = new ArrayList<>();
        }
        ModelAndView modelAndView = new ModelAndView("results");
        modelAndView.addObject("calculations", calculations);
        return modelAndView;
    }
}


