package ru.hw1.service;

import ru.hw1.collection.MyArrayList;
import ru.hw1.model.Car;

import java.util.Comparator;
import java.util.List;

public class CarService {

    private final List<Car> cars = new MyArrayList<>();

    public void add(Car car) {
        cars.add(car);
    }

    public void addAll(List<Car> newCars) {
        for (Car car : newCars) {
            cars.add(car);
        }
    }

    public Car get(int index) {
        return cars.get(index);
    }

    public Car remove(int index) {
        return cars.remove(index);
    }

    public int size() {
        return cars.size();
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<String> process(int minPower) {
        return cars.stream()
                .filter(car -> car.getPower() >= minPower)
                .sorted(Comparator.comparingInt(Car::getYear))
                .map(car -> car.getYear() + " - " + car.getModel().toUpperCase() + " (" + car.getPower() + " л.с.)")
                .toList();
    }
}
