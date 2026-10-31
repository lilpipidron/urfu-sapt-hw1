package ru.hw1.service;

import ru.hw1.collection.MyArrayList;
import ru.hw1.model.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CarFileReader {

    public List<Car> read(String fileName) throws IOException {
        List<Car> result = new MyArrayList<>();
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                try {
                    result.add(parseCar(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Строка " + lineNumber + " пропущена: " + e.getMessage());
                }
            }
        }
        return result;
    }

    private Car parseCar(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("должно быть 3 значения через запятую");
        }
        int power;
        int year;
        try {
            power = Integer.parseInt(parts[1].trim());
            year = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("мощность и год должны быть числами");
        }
        return new Car(parts[0], power, year);
    }
}
