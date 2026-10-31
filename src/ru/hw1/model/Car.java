package ru.hw1.model;

public class Car {

    public static final int MIN_POWER = 1;
    public static final int MAX_POWER = 2000;
    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = 2026;

    private String model;
    private int power;
    private int year;

    public Car(String model, int power, int year) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("модель не может быть пустой");
        }
        if (power < MIN_POWER || power > MAX_POWER) {
            throw new IllegalArgumentException("мощность должна быть от " + MIN_POWER + " до " + MAX_POWER);
        }
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("год выпуска должен быть от " + MIN_YEAR + " до " + MAX_YEAR);
        }
        this.model = model.trim();
        this.power = power;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', power=" + power + ", year=" + year + "}";
    }
}
